package ru.edu.kolyanpie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import ru.edu.kolyanpie.Hex;
import ru.edu.kolyanpie.model.Field;

public class LocalGameScreen implements Screen {
    private final Hex hex;
    private Field stage;
    private boolean blueTurn;

    public LocalGameScreen(Hex hex) {
        this.hex = hex;
    }

    @Override
    public void show() {
        stage = new Field(this::cellClicked);
        Gdx.input.setInputProcessor(stage);
        blueTurn = true;
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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

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
        } else {
            if (!stage.setRed(i, j)) {
                return;
            }
        }
        blueTurn = !blueTurn;
    }
}
