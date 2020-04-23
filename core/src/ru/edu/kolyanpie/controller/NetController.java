package ru.edu.kolyanpie.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.Json;

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
        getRequest.setHeader("Authorization", PreferencesController.getHttpBasicAuthorization());
        Gdx.net.sendHttpRequest(getRequest, listener);
    }

    public static void sendJson(String path, String json, Net.HttpResponseListener listener) {
        jsonRequest.setUrl(url + path);
        jsonRequest.setContent(json);
        jsonRequest.setHeader("Authorization", PreferencesController.getHttpBasicAuthorization());
        Gdx.net.sendHttpRequest(jsonRequest, listener);
    }

    private NetController() {}
}
