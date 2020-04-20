package ru.edu.kolyanpie;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ru.edu.kolyanpie.view.screen.LocalGameScreen;
import ru.edu.kolyanpie.view.ui.MenuScreen;

import java.util.HashMap;

public class Hex extends Game {
    private HashMap<Class<? extends Screen>, Screen> screens;

    @Override
    public void create() {
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            Gdx.input.setCatchKey(Input.Keys.BACK, true);
            Gdx.input.setCatchKey(Input.Keys.MENU, true);
        }
        Vars.game = this;
        Vars.skin = new Skin(Gdx.files.internal("ui/skin.json"));
        screens = screensInit();
        setScreen(getScreen(MenuScreen.class));
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

    public Screen getScreen(Class<? extends Screen> clz) {
        return screens.get(clz);
    }

    private HashMap<Class<? extends Screen>, Screen> screensInit() {
        HashMap<Class<? extends Screen>, Screen> result = new HashMap<>();

        result.put(MenuScreen.class, new MenuScreen(Vars.skin));
        result.put(LocalGameScreen.class, new LocalGameScreen());
        return result;
    }
}
