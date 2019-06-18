package ru.edu.kolyanpie.view.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.edu.kolyanpie.view.MenuScreen;

class LanMenuTable extends Table {
    private final MenuScreen menuScreen;
    private final Skin skin;
    private TextButton backButton;

    LanMenuTable(Skin skin, MenuScreen menuScreen) {
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
        backButton = new TextButton("BACK", skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.changeMenu(new MainMenuTable(skin, menuScreen));
            }
        });
    }

}
