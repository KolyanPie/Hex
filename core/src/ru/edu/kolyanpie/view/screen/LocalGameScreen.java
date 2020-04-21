package ru.edu.kolyanpie.view.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import net.ddns.ktgd.menu.Menu;
import net.ddns.ktgd.menu.MenuStage;
import ru.edu.kolyanpie.Vars;
import ru.edu.kolyanpie.controller.WinChecker;
import ru.edu.kolyanpie.model.Field;
import ru.edu.kolyanpie.model.State;
import ru.edu.kolyanpie.view.ui.MenuScreen;

import java.util.ArrayList;
import java.util.List;

public class LocalGameScreen implements Screen {
    private Field stage;
    private MenuStage pauseStage;
    private List<List<State>> cells;
    private boolean blueTurn;
    private boolean isPause;

    @Override
    public void show() {
        stage = new Field(this::cellClicked);
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.MENU || keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    pauseGame();
                    return true;
                }
                return false;
            }
        });
        pauseStage = createPauseStage();
        Gdx.input.setInputProcessor(stage);
        blueTurn = true;
        cells = new ArrayList<>(11);
        for (int i = 0; i < 11; i++) {
            cells.add(new ArrayList<>(11));
            for (int j = 0; j < 11; j++) {
                cells.get(i).add(State.EMPTY);
            }
        }
        isPause = false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        if (!isPause) {
            stage.act(delta);
        } else {
            pauseStage.draw();
            pauseStage.act(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        pauseStage.resize(width, height);
        stage.resize(width, height);
    }

    @Override
    public void pause() {
        pauseGame();
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            Gdx.input.setInputProcessor(null);
            stage.dispose();
            pauseStage.dispose();
        }
    }

    @Override
    public void resume() {
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            stage = new Field(this::cellClicked, cells);
            pauseStage = createPauseStage();
            Gdx.input.setInputProcessor(stage);
        }
        stage.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        pauseStage.dispose();
    }

    private void cellClicked(int i, int j) {
        if (blueTurn) {
            if (!stage.setBlue(i, j)) {
                return;
            }
            cells.get(i).set(j, State.BLUE);
        } else {
            if (!stage.setRed(i, j)) {
                return;
            }
            cells.get(i).set(j, State.RED);
        }
        blueTurn = !blueTurn;
        if (WinChecker.checkWin(cells, i, j)) {
            pauseStage = createWinStage(cells.get(i).get(j));
            pauseGame();
        }
    }

    private void pauseGame() {
        isPause = true;
        pauseStage.show();
    }

    private void resumeGame() {
        isPause = false;
        Gdx.input.setInputProcessor(stage);
    }

    private MenuStage createPauseStage() {
        Actor pauseLabel;
        Actor continueButton;
        Actor backToMenuButton;

        pauseLabel = new Label("PAUSE", Vars.skin, "black") {{
            setAlignment(Align.center);
        }};
        continueButton = Menu.getClickedActor(
                new TextButton("CONTINUE", Vars.skin, "black"),
                event -> resumeGame()
        );
        backToMenuButton = Menu.getClickedActor(
                new TextButton("BACK TO MENU", Vars.skin, "black"),
                event -> Vars.game.setScreen(Vars.game.getScreen(MenuScreen.class))
        );
        return createResumableMenuStage(pauseLabel, continueButton, backToMenuButton);
    }

    private MenuStage createWinStage(State state) {
        Label winnerLabel;
        Actor backToMenuButton;

        winnerLabel = new Label("YOU WIN", Vars.skin, state.equals(State.BLUE) ? "blue" : "red") {{
            setAlignment(Align.center);
        }};
        backToMenuButton = Menu.getClickedActor(
                new TextButton("BACK TO MENU", Vars.skin, "black"),
                event -> Vars.game.setScreen(Vars.game.getScreen(MenuScreen.class))
        );
        return createResumableMenuStage(winnerLabel, backToMenuButton);
    }

    private MenuStage createResumableMenuStage(Actor... actors) {
        MenuStage stage = new MenuStage(actors);

        stage.addKeyDownListener(keycode -> {
            if (keycode == Input.Keys.MENU || keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                resumeGame();
                return true;
            }
            return false;
        });
        return stage;
    }
}
