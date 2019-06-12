package ru.edu.kolyanpie.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.edu.kolyanpie.Hex;

public class DesktopLauncher {
    public static void main(String[] arg) {
        System.setProperty("user.name", "name");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1080;
        config.height = 1920;
        new LwjglApplication(new Hex(), config);
    }
}
