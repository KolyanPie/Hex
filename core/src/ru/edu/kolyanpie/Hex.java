package ru.edu.kolyanpie;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ru.edu.kolyanpie.view.ui.MenuScreen;

public class Hex extends Game {

    @Override
    public void create() {
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            Gdx.input.setCatchKey(Input.Keys.BACK, true);
            Gdx.input.setCatchKey(Input.Keys.MENU, true);
        }
        Vars.game = this;
        Vars.skin = new Skin(Gdx.files.internal("ui/skin.json"));
        setScreen(new MenuScreen(Vars.skin));
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
}
