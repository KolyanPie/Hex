package ru.edu.kolyanpie.controller;

import com.badlogic.gdx.utils.Base64Coder;

import static ru.edu.kolyanpie.util.Preferences.*;

public final class PreferencesController {

    private PreferencesController() {}

    public static String getHttpBasicAuthorization() {
        return "Basic "
                + Base64Coder.encodeString(
                HEX_PREF.getString(NAME, NAME_DEFAULT)
                        + ":"
                        + HEX_PREF.getString(PASS, PASS_DEFAULT)
        );
    }
}
