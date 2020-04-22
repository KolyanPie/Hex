package ru.edu.kolyanpie.view.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import net.ddns.ktgd.menu.Menu;
import net.ddns.ktgd.menu.MenuStage;
import ru.edu.kolyanpie.Vars;

public final class NetMenuScreen extends Menu {
    private static final NetMenuScreen instance = new NetMenuScreen();

    //region menu's items
    //Main net menu
    private final Actor LOGIN_NAME_LABEL;
    private final TextField LOGIN_NAME_FIELD;
    private final Actor LOGIN_PASS_LABEL;
    private final TextField LOGIN_PASS_FIELD;
    private final Actor LOGIN_LOGIN_BUTTON;
    private final Actor LOGIN_REGISTRATION_BUTTON;
    private final Actor LOGIN_BACK_TO_MAIN_MENU_BUTTON;
    //Registration menu
    private final Actor REGISTRATION_NAME_LABEL;
    private final TextField REGISTRATION_NAME_FIELD;
    private final Actor REGISTRATION_PASS_LABEL;
    private final TextField REGISTRATION_PASS_FIELD;
    private final Actor REGISTRATION_CONFIRM_PASS_LABEL;
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
            //TODO: login request + alert/next menu
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
        REGISTRATION_NAME_FIELD = new TextField("", uiSkin);
        REGISTRATION_PASS_LABEL = new Label("WRITE PASSWORD", uiSkin) {{
            setAlignment(Align.center);
        }};
        REGISTRATION_PASS_FIELD = new TextField("", uiSkin);
        REGISTRATION_CONFIRM_PASS_LABEL = new Label("CONFIRM PASSWORD", uiSkin) {{
            setAlignment(Align.center);
        }};
        REGISTRATION_CONFIRM_PASS_FIELD = new TextField("", uiSkin);
        REGISTRATION_CONTINUE_BUTTON = getClickedActor(new TextButton("CONTINUE", uiSkin), event -> {
            //TODO: check alert + registration request
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
}
