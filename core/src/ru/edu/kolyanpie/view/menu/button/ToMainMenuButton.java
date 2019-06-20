package ru.edu.kolyanpie.view.menu.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.edu.kolyanpie.view.MenuScreen;
import ru.edu.kolyanpie.view.menu.MainMenuTable;

public class ToMainMenuButton extends TextButton {
    public ToMainMenuButton(String text, Skin skin, MenuScreen menuScreen) {
        super(text, skin);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.changeMenu(new MainMenuTable(skin, menuScreen));
            }
        });
    }
}
