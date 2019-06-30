package ru.edu.kolyanpie.view.screen;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.ArrayList;
import java.util.List;

import ru.edu.kolyanpie.Hex;
import ru.edu.kolyanpie.controller.WinChecker;
import ru.edu.kolyanpie.model.Field;
import ru.edu.kolyanpie.model.State;
import ru.edu.kolyanpie.view.menu.PauseMenuTable;
import ru.edu.kolyanpie.view.menu.WinMenuTable;

public class LocalGameScreen implements Screen {
    private final Hex hex;
    private Field stage;
    private Stage pauseStage;
    private List<List<State>> cells;
    private boolean blueTurn;
    private boolean isPause;

    public LocalGameScreen(Hex hex) {
        this.hex = hex;
    }

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
        stage.resize(width, height);
    }

    @Override
    public void pause() {
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
        pauseGame();
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
        Gdx.input.setInputProcessor(pauseStage);
    }

    private void resumeGame() {
        isPause = false;
        Gdx.input.setInputProcessor(stage);
    }

    private Stage createPauseStage() {
        Stage stage = new Stage(this.stage.getViewport());
        PauseMenuTable table = new PauseMenuTable(new Skin(Gdx.files.internal("ui/skin.json")), hex, this::resumeGame);
        table.setFillParent(true);
        stage.addActor(table);
        stage.setKeyboardFocus(table);
        return stage;
    }

    private Stage createWinStage(State state) {
        Stage stage = new Stage(this.stage.getViewport());
        WinMenuTable table = new WinMenuTable(new Skin(Gdx.files.internal("ui/skin.json")), hex, true, state.equals(State.BLUE));
        table.setFillParent(true);
        stage.addActor(table);
        stage.setKeyboardFocus(table);
        return stage;
    }
}
