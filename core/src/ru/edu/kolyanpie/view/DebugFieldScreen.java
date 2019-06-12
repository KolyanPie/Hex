package ru.edu.kolyanpie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import ru.edu.kolyanpie.model.Cell;

public class DebugFieldScreen implements Screen {
    private Stage stage = new Stage(new FitViewport(1080, 1920));

    @Override
    public void show() {
        Cell cell;
        for (int j = 0; j < 28; j++) {
            cell = new Cell();
            cell.setBounds(-20, 34 + j * 68, 38, 41);
            stage.addActor(cell);
            for (int i = 0; i < 26; i++) {
                cell = new Cell();
                cell.setBounds(i * 40, j * 68, 38, 41);
                stage.addActor(cell);
                cell = new Cell();
                cell.setBounds(20 + i * 40, 34 + j * 68, 38, 41);
                stage.addActor(cell);
            }
        }
        Gdx.input.setInputProcessor(stage);
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
        stage.getViewport().update(width, height);
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
}
