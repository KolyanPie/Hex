package ru.edu.kolyanpie.view.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.ddns.ktgd.menu.Menu;
import net.ddns.ktgd.menu.MenuStage;
import ru.edu.kolyanpie.Vars;
import ru.edu.kolyanpie.view.screen.LocalGameScreen;

public class MenuScreen extends Menu {

    //region menu's items
    //Main menu
    private final Actor LOCAL_MENU_BUTTON;
    private final Actor LAN_MENU_BUTTON;
    private final Actor NET_MENU_BUTTON;
    private final Actor ABOUT_MENU_BUTTON;
    private final Actor QUIT_BUTTON;
    //Lan menu
    private final Actor LAN_NOT_AVAILABLE_LABEL;
    private final Actor LAN_BACK_TO_MAIN_MENU_BUTTON;
    //Net menu
    private final Actor NET_NOT_AVAILABLE_LABEL;
    private final Actor NET_BACK_TO_MAIN_MENU_BUTTON;
    //About menu
    private final Actor WIKI_URL_BUTTON;
    private final Actor GITHUB_URL_BUTTON;
    private final Actor ABOUT_BACK_TO_MAIN_MENU_BUTTON;
    //endregion

    //region menus
    private MenuStage MAIN_MENU;
    private MenuStage LAN_MENU;
    private MenuStage NET_MENU;
    private MenuStage ABOUT_MENU;
    //endregion

    public MenuScreen(Skin menuSkin) {
        super(menuSkin);
        //Main menu
        LOCAL_MENU_BUTTON = getClickedActor(
                new TextButton("LOCAL", uiSkin),
                event -> Vars.game.setScreen(new LocalGameScreen())
        );
        LAN_MENU_BUTTON = getClickedActor(new TextButton("LAN", uiSkin), event -> changeMenu(LAN_MENU));
        NET_MENU_BUTTON = getClickedActor(new TextButton("NET", uiSkin), event -> changeMenu(NET_MENU));
        ABOUT_MENU_BUTTON = getClickedActor(new TextButton("ABOUT", uiSkin), event -> changeMenu(ABOUT_MENU));
        QUIT_BUTTON = getClickedActor(new TextButton("QUIT", uiSkin), event -> Gdx.app.exit());
        //Lan menu
        LAN_NOT_AVAILABLE_LABEL = new Label("NOT AVAILABLE YET", uiSkin);
        LAN_BACK_TO_MAIN_MENU_BUTTON = getClickedActor(new TextButton("BACK", uiSkin), event -> changeMenu(MAIN_MENU));
        //Net menu
        NET_NOT_AVAILABLE_LABEL = new Label("NOT AVAILABLE YET", uiSkin);
        NET_BACK_TO_MAIN_MENU_BUTTON = getClickedActor(new TextButton("BACK", uiSkin), event -> changeMenu(MAIN_MENU));
        //About menu
        WIKI_URL_BUTTON = getClickedActor(
                new TextButton("->WIKI", uiSkin, "link"),
                event -> Gdx.net.openURI("https://en.wikipedia.org/wiki/Hex_(board_game)")
        );
        GITHUB_URL_BUTTON = getClickedActor(
                new TextButton("->GITHUB", uiSkin, "link"),
                event -> Gdx.net.openURI("https://en.wikipedia.org/wiki/Hex_(board_game)")
        );
        ABOUT_BACK_TO_MAIN_MENU_BUTTON = getClickedActor(new TextButton("BACK", uiSkin), event -> changeMenu(MAIN_MENU));
    }

    @Override
    public void show() {
        MAIN_MENU = new MenuStage(LOCAL_MENU_BUTTON, LAN_MENU_BUTTON, NET_MENU_BUTTON, ABOUT_MENU_BUTTON, QUIT_BUTTON);
        LAN_MENU = new MenuStage(LAN_NOT_AVAILABLE_LABEL, LAN_BACK_TO_MAIN_MENU_BUTTON);
        NET_MENU = new MenuStage(NET_NOT_AVAILABLE_LABEL, NET_BACK_TO_MAIN_MENU_BUTTON);
        ABOUT_MENU = new MenuStage(WIKI_URL_BUTTON, GITHUB_URL_BUTTON, ABOUT_BACK_TO_MAIN_MENU_BUTTON);
        changeMenu(MAIN_MENU);
    }

    @Override
    public void hide() {
        MAIN_MENU.dispose();
        MAIN_MENU = null;
        LAN_MENU.dispose();
        LAN_MENU = null;
        NET_MENU.dispose();
        NET_MENU = null;
        ABOUT_MENU.dispose();
        ABOUT_MENU = null;
    }
}
