package ru.edu.kolyanpie;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ru.edu.kolyanpie.view.ui.MenuScreen;

public class Hex extends Game {
    private MenuScreen menuScreen;

    @Override
    public void create() {
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            Gdx.input.setCatchKey(Input.Keys.BACK, true);
            Gdx.input.setCatchKey(Input.Keys.MENU, true);
        }
        Vars.game = this;
        Vars.skin = new Skin(Gdx.files.internal("ui/skin.json"));
        menuScreen = new MenuScreen(Vars.skin);
        setScreen(menuScreen);
    }

    @Override
    public void pause() {
        super.pause();
        Vars.skin.dispose();
    }

    @Override
    public void resume() {
        super.resume();
        Vars.skin.load(Gdx.files.internal("ui/skin.json"));
    }

    public MenuScreen getMenuScreen() {
        return menuScreen;
    }
}
