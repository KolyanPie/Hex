package ru.edu.kolyanpie.util;

import com.badlogic.gdx.Gdx;

public enum Preferences {

    HEX_PREF("kolyanpie/hex-pref");

    public static final String NAME = "name";
    public static final String PASS = "pass";

    public static final String NAME_DEFAULT = "";
    public static final String PASS_DEFAULT = "";

    private final com.badlogic.gdx.Preferences prefs;

    Preferences(String file) {
        prefs = Gdx.app.getPreferences(file);
    }

    public int getInt(String key, int defValue) {
        return prefs.getInteger(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return prefs.getBoolean(key, defValue);
    }

    public String getString(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

    public void put(String key, int value) {
        prefs.putInteger(key, value);
        prefs.flush();
    }

    public void put(String key, boolean value) {
        prefs.putBoolean(key, value);
        prefs.flush();
    }

    public void put(String key, String value) {
        prefs.putString(key, value);
        prefs.flush();
    }
}
