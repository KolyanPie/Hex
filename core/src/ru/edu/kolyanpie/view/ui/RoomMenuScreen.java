package ru.edu.kolyanpie.view.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.ddns.ktgd.menu.Menu;
import net.ddns.ktgd.menu.MenuStage;
import ru.edu.kolyanpie.Vars;

public class RoomMenuScreen extends Menu {
    private static final RoomMenuScreen instance = new RoomMenuScreen();

    //region menu's items
    //Main menu
    private final Actor CREATE_ROOM_BUTTON;
    private final Actor FIND_ROOM_BUTTON;
    private final Actor JOIN_ROOM_BUTTON;
    private final Actor MAIN_MENU_BUTTON;

    //region menus
    private MenuStage ROOM_MAIN_MENU;
    //endregion

    public static RoomMenuScreen getInstance() {
        return instance;
    }

    private RoomMenuScreen() {
        super(Vars.skin);

        //region main menu
        CREATE_ROOM_BUTTON = getClickedActor(
                new TextButton("CREATE ROOM", uiSkin),
                event -> {
                    //TODO
                }
        );
        FIND_ROOM_BUTTON = getClickedActor(
                new TextButton("FIND ROOM", uiSkin),
                event -> {
                    //TODO
                }
        );
        JOIN_ROOM_BUTTON = getClickedActor(
                new TextButton("JOIN BY ID", uiSkin),
                event -> {
                    //TODO
                }
        );
        MAIN_MENU_BUTTON = getClickedActor(
                new TextButton("MAIN MENU", uiSkin),
                event -> Vars.game.setScreen(MenuScreen.getInstance())
        );
        //endregion

    }

    @Override
    public void show() {
        ROOM_MAIN_MENU = new MenuStage(CREATE_ROOM_BUTTON, MAIN_MENU_BUTTON);
        changeMenu(ROOM_MAIN_MENU);
    }

    @Override
    public void hide() {
        ROOM_MAIN_MENU.dispose();
        ROOM_MAIN_MENU = null;
    }
}
