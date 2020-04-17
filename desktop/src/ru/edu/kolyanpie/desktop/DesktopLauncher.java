package ru.edu.kolyanpie.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.edu.kolyanpie.Hex;

public class DesktopLauncher {
    public static void main(String[] arg) {
        System.setProperty("user.name", "name");
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        new Lwjgl3Application(new Hex(), config);
    }
}
