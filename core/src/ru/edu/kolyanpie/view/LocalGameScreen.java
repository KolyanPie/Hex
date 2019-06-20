package ru.edu.kolyanpie.view;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.List;

import ru.edu.kolyanpie.Hex;
import ru.edu.kolyanpie.controller.WinChecker;
import ru.edu.kolyanpie.model.Field;
import ru.edu.kolyanpie.model.State;

public class LocalGameScreen implements Screen {
    private final Hex hex;
    private Field stage;
    private List<List<State>> cells;
    private boolean blueTurn;

    public LocalGameScreen(Hex hex) {
        this.hex = hex;
    }

    @Override
    public void show() {
        stage = new Field(this::cellClicked);
        Gdx.input.setInputProcessor(stage);
        blueTurn = true;
        cells = new ArrayList<>(11);
        for (int i = 0; i < 11; i++) {
            cells.add(new ArrayList<>(11));
            for (int j = 0; j < 11; j++) {
                cells.get(i).add(State.EMPTY);
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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
        }
    }

    @Override
    public void resume() {
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            stage = new Field(this::cellClicked, cells);
            Gdx.input.setInputProcessor(stage);
        }
        stage.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void hide() {
        Gdx.app.exit();
    }

    @Override
    public void dispose() {
        stage.dispose();
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
    }
}
