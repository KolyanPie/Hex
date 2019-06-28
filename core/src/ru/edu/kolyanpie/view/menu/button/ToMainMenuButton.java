package ru.edu.kolyanpie.view.menu.button;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.edu.kolyanpie.view.menu.MainMenuTable;
import ru.edu.kolyanpie.view.menu.MenuChangeable;

public class ToMainMenuButton extends TextButton {
    public ToMainMenuButton(String text, Skin skin, MenuChangeable menuChangeable) {
        super(text, skin);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuChangeable.changeMenu(new MainMenuTable(skin, menuChangeable));
            }
        });
    }

    public ToMainMenuButton(String text, Skin skin, MenuChangeable menuChangeable, String styleName) {
        super(text, skin, styleName);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuChangeable.changeMenu(new MainMenuTable(skin, menuChangeable));
            }
        });
    }
}
