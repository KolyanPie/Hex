package ru.edu.kolyanpie.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import net.ddns.ktgd.menu.Menu;
import net.ddns.ktgd.menu.MenuStage;
import ru.edu.kolyanpie.Vars;
import ru.edu.kolyanpie.controller.NetController;

public final class NetMenuScreen extends Menu {
    private static final NetMenuScreen instance = new NetMenuScreen();

    //region menu's items
    //Main net menu
    private final Label LOGIN_NAME_LABEL;
    private final TextField LOGIN_NAME_FIELD;
    private final Actor LOGIN_PASS_LABEL;
    private final TextField LOGIN_PASS_FIELD;
    private final Actor LOGIN_LOGIN_BUTTON;
    private final Actor LOGIN_REGISTRATION_BUTTON;
    private final Actor LOGIN_BACK_TO_MAIN_MENU_BUTTON;
    //Registration menu
    private final Label REGISTRATION_NAME_LABEL;
    private final TextField REGISTRATION_NAME_FIELD;
    private final Label REGISTRATION_PASS_LABEL;
    private final TextField REGISTRATION_PASS_FIELD;
    private final Label REGISTRATION_CONFIRM_PASS_LABEL;
    private final TextField REGISTRATION_CONFIRM_PASS_FIELD;
    private final Actor REGISTRATION_CONTINUE_BUTTON;
    private final Actor REGISTRATION_CANCEL_BUTTON;
    //endregion

    //region menus
    private MenuStage LOGIN_NET_MENU;
    private MenuStage REGISTRATION_NET_MENU;
    //endregion

    public static NetMenuScreen getInstance() {
        return instance;
    }

    private NetMenuScreen() {
        super(Vars.skin);

        //region login menu
        LOGIN_NAME_LABEL = new Label("NAME", uiSkin) {{
            setAlignment(Align.center);
        }};
        LOGIN_NAME_FIELD = new TextField("", uiSkin);
        LOGIN_PASS_LABEL = new Label("PASSWORD", uiSkin) {{
            setAlignment(Align.center);
        }};
        LOGIN_PASS_FIELD = new TextField("", uiSkin) {{
            setPasswordMode(true);
            setPasswordCharacter('*');
        }};
        LOGIN_LOGIN_BUTTON = getClickedActor(new TextButton("LOG IN", uiSkin), event -> {
            NetController.updateAuthorization(LOGIN_NAME_FIELD.getText(), LOGIN_PASS_FIELD.getText());
            NetController.sendGet("/login", "", new Net.HttpResponseListener() {
                @Override
                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                    if (httpResponse.getResultAsString().equals("true")) {
                        Vars.game.setScreen(RoomMenuScreen.getInstance());
                    } else {
                        setStyleAndText(LOGIN_NAME_LABEL, "red", "BAD CREDENTIALS");
                    }
                }

                @Override
                public void failed(Throwable t) {
                    setStyleAndText(LOGIN_NAME_LABEL, "red", "CONNECT TROUBLE");
                }

                @Override
                public void cancelled() {}
            });
        });
        LOGIN_REGISTRATION_BUTTON = getClickedActor(
                new TextButton("REGISTRATION", uiSkin),
                event -> changeMenu(REGISTRATION_NET_MENU)
        );
        LOGIN_BACK_TO_MAIN_MENU_BUTTON = getClickedActor(
                new TextButton("BACK", uiSkin),
                event -> Vars.game.setScreen(MenuScreen.getInstance())
        );
        //endregion

        //region registration menu
        REGISTRATION_NAME_LABEL = new Label("WRITE NAME", uiSkin) {{
            setAlignment(Align.center);
        }};
        REGISTRATION_NAME_FIELD = new TextField("", uiSkin) {{
            addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    String text = REGISTRATION_NAME_FIELD.getText();
                    NetController.sendGet(
                            "/username",
                            "username=" + text.replaceAll(" ", "+"),
                            new Net.HttpResponseListener() {
                                @Override
                                public void handleHttpResponse(Net.HttpResponse httpResponse) {
                                    switch (httpResponse.getResultAsString()) {
                                        case "true":
                                            setStyleAndText(REGISTRATION_NAME_LABEL, "green", "NAME IS AVAILABLE");
                                            break;
                                        case "false":
                                            setStyleAndText(REGISTRATION_NAME_LABEL, "red", "IS NOT AVAILABLE");
                                    }
                                }

                                @Override
                                public void failed(Throwable t) {}

                                @Override
                                public void cancelled() {}
                            }
                    );
                    setTextFieldFilter(
                            (textField, c) -> Character.isDigit(c) || c == 32 || (65 <= c && c <= 90) || (97 <= c && c <= 122)
                    );
                }
            });
        }};
        REGISTRATION_PASS_LABEL = new Label("WRITE PASSWORD", uiSkin) {{
            setAlignment(Align.center);
        }};
        REGISTRATION_PASS_FIELD = new TextField("", uiSkin) {{
            setPasswordCharacter('*');
            setPasswordMode(true);
            addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (isPassAvailable()) {
                        setStyleAndText(REGISTRATION_PASS_LABEL, "green", "PASS IS AVAILABLE");
                    } else {
                        setStyleAndText(REGISTRATION_PASS_LABEL, "red", "LENGTH MUST BE > 7");
                    }
                    if (isPassEquals()) {
                        setStyleAndText(REGISTRATION_CONFIRM_PASS_LABEL, "green", "EQUALS");
                    } else {
                        setStyleAndText(REGISTRATION_CONFIRM_PASS_LABEL, "red", "NOT EQUALS");
                    }
                }
            });
        }};
        REGISTRATION_CONFIRM_PASS_LABEL = new Label("CONFIRM PASSWORD", uiSkin) {{
            setAlignment(Align.center);
        }};
        REGISTRATION_CONFIRM_PASS_FIELD = new TextField("", uiSkin) {{
            setPasswordCharacter('*');
            setPasswordMode(true);
            addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (isPassEquals()) {
                        setStyleAndText(REGISTRATION_CONFIRM_PASS_LABEL, "green", "EQUALS");
                    } else {
                        setStyleAndText(REGISTRATION_CONFIRM_PASS_LABEL, "red", "NOT EQUALS");
                    }
                }
            });
        }};
        REGISTRATION_CONTINUE_BUTTON = getClickedActor(new TextButton("CONTINUE", uiSkin), event -> {
            String name = REGISTRATION_NAME_FIELD.getText();
            String pass = REGISTRATION_PASS_FIELD.getText();
            NetController.sendJson(
                    "/registration",
                    String.format(
                            "{\"username\":\"%s\", \"password\":\"%s\"}",
                            name,
                            pass
                    ),
                    new Net.HttpResponseListener() {
                        @Override
                        public void handleHttpResponse(Net.HttpResponse httpResponse) {
                            if (httpResponse.getResultAsString().equals("true")) {
                                LOGIN_NAME_FIELD.setText(name);
                                LOGIN_PASS_FIELD.setText(pass);
                                Gdx.app.postRunnable(() -> changeMenu(LOGIN_NET_MENU));
                            }
                        }

                        @Override
                        public void failed(Throwable t) {}

                        @Override
                        public void cancelled() {}
                    }
            );
        });
        REGISTRATION_CANCEL_BUTTON = getClickedActor(
                new TextButton("CANCEL", uiSkin),
                event -> changeMenu(LOGIN_NET_MENU)
        );
        //endregion
    }

    @Override
    public void show() {
        LOGIN_NET_MENU = new MenuStage(
                LOGIN_NAME_LABEL,
                LOGIN_NAME_FIELD,
                LOGIN_PASS_LABEL,
                LOGIN_PASS_FIELD,
                LOGIN_LOGIN_BUTTON,
                LOGIN_REGISTRATION_BUTTON,
                LOGIN_BACK_TO_MAIN_MENU_BUTTON
        );
        REGISTRATION_NET_MENU = new MenuStage(
                REGISTRATION_NAME_LABEL,
                REGISTRATION_NAME_FIELD,
                REGISTRATION_PASS_LABEL,
                REGISTRATION_PASS_FIELD,
                REGISTRATION_CONFIRM_PASS_LABEL,
                REGISTRATION_CONFIRM_PASS_FIELD,
                REGISTRATION_CONTINUE_BUTTON,
                REGISTRATION_CANCEL_BUTTON
        );
        changeMenu(LOGIN_NET_MENU);
    }

    @Override
    public void hide() {
        LOGIN_NET_MENU.dispose();
        LOGIN_NET_MENU = null;
        REGISTRATION_NET_MENU.dispose();
        REGISTRATION_NET_MENU = null;
    }

    private boolean isPassAvailable() {
        return REGISTRATION_PASS_FIELD.getText().length() > 7;
    }

    private boolean isPassEquals() {
        return REGISTRATION_CONFIRM_PASS_FIELD.getText().equals(REGISTRATION_PASS_FIELD.getText());
    }

    private void setStyleAndText(Label label, String style, String text) {
        label.setStyle(uiSkin.get(
                style,
                Label.LabelStyle.class
        ));
        label.setText(text);
    }
}
