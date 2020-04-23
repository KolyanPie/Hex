package ru.edu.kolyanpie.controller;

import com.badlogic.gdx.utils.Base64Coder;

import static ru.edu.kolyanpie.util.Preferences.*;

public final class PreferencesController {

    private PreferencesController() {}

    public static void updateAuthorization(String name, String pass) {
        HEX_PREF.put(AUTHORIZATION, Base64Coder.encodeString(name + ":" + pass));
    }

    public static String getAuthorizationName() {
        return Base64Coder.decodeString(HEX_PREF.getString(AUTHORIZATION, AUTHORIZATION_DEFAULT)).split(":")[0];
    }

    public static String getAuthorizationPass() {
        return Base64Coder.decodeString(HEX_PREF.getString(AUTHORIZATION, AUTHORIZATION_DEFAULT)).split(":")[1];
    }
}
