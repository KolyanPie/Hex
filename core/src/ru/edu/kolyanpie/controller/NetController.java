package ru.edu.kolyanpie.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import ru.edu.kolyanpie.Vars;

public final class NetController {
    private static final Net.HttpRequest jsonRequest = new Net.HttpRequest(Net.HttpMethods.POST) {{
        setHeader("Content-Type", "application/json");
        setHeader("Accept", "application/json");
    }};
    private static final Net.HttpRequest getRequest = new Net.HttpRequest(Net.HttpMethods.GET);
    private static final String url = new Json().fromJson(String.class, Gdx.files.internal("properties/url.json"));

    public static void sendGet(String path, String params, Net.HttpResponseListener listener) {
        getRequest.setUrl(url + path);
        getRequest.setContent(params);
        getRequest.setHeader("Authorization", "Basic " + Vars.preferences.getString("Authorization", "MTox"));
        Gdx.net.sendHttpRequest(getRequest, listener);
    }

    public static void sendJson(String path, String json, Net.HttpResponseListener listener) {
        jsonRequest.setUrl(url + path);
        jsonRequest.setContent(json);
        jsonRequest.setHeader("Authorization", "Basic " + Vars.preferences.getString("Authorization", "MTox"));
        Gdx.net.sendHttpRequest(jsonRequest, listener);
    }

    public static void updateAuthorization(String name, String pass) {
        Vars.preferences.putString("Authorization", Base64Coder.encodeString(name + ":" + pass));
        Vars.preferences.flush();
    }

    private NetController() {}
}
