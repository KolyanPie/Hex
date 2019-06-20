package ru.edu.kolyanpie.view.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.edu.kolyanpie.view.MenuScreen;
import ru.edu.kolyanpie.view.menu.button.ToMainMenuButton;

class NetMenuTable extends Table {
    private final MenuScreen menuScreen;
    private final Skin skin;
    private TextButton backButton;

    NetMenuTable(Skin skin, MenuScreen menuScreen) {
        super(skin);
        this.menuScreen = menuScreen;
        this.skin = getSkin();
        initialize();
        add(backButton);
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    menuScreen.changeMenu(new MainMenuTable(skin, menuScreen));
                    return true;
                }
                return false;
            }
        });
    }

    private void initialize() {
        backButton = new ToMainMenuButton("BACK", skin, menuScreen);
    }
}
