package ru.edu.kolyanpie;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import ru.edu.kolyanpie.view.MenuScreen;

public class Hex extends Game {

    @Override
    public void create() {
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
        }
        setScreen(new MenuScreen(this));
    }

    public void changeScreen(Screen screen) {
        Screen tmp = getScreen();
        setScreen(screen);
        tmp.dispose();
    }
}
