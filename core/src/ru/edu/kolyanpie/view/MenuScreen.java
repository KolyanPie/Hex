package ru.edu.kolyanpie.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import ru.edu.kolyanpie.Hex;
import ru.edu.kolyanpie.view.menu.MainMenuTable;
import ru.edu.kolyanpie.view.menu.MenuChangeable;

public class MenuScreen implements Screen, MenuChangeable {
    private final Hex hex;
    private Stage stage;

    public MenuScreen(Hex hex) {
        this.hex = hex;
    }

    @Override
    public void show() {
        initialize();
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
        stage.getViewport().update(width, height, true);
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

    @Override
    public void changeMenu(Table table) {
        table.setFillParent(true);
        stage.clear();
        stage.addActor(table);
        stage.setKeyboardFocus(table);
    }

    public void changeScreen(Screen screen) {
        hex.changeScreen(screen);
    }

    public Hex getHex() {
        return hex;
    }

    private void initialize() {
        stage = new Stage(new FitViewport(600, 800));
        Skin skin = new Skin(Gdx.files.internal("ui/skin.json"));
        Gdx.input.setInputProcessor(stage);
        changeMenu(new MainMenuTable(skin, this));
    }
}
