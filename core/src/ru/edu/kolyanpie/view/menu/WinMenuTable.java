package ru.edu.kolyanpie.view.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import ru.edu.kolyanpie.view.menu.button.ToMainMenuButton;

public class WinMenuTable extends Table {
    private final MenuChangeable menuChangeable;
    private final Skin skin;
    private Label winnerLabel;
    private TextButton backToMenuButton;

    public WinMenuTable(Skin skin, MenuChangeable menuChangeable, boolean youWin, boolean isBlue) {
        super(skin);
        this.menuChangeable = menuChangeable;
        this.skin = getSkin();
        initialize(youWin, isBlue);
        add(winnerLabel);
        row();
        add(backToMenuButton);
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.MENU || keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    menuChangeable.changeMenu(new MainMenuTable(skin, menuChangeable));
                    return true;
                }
                return false;
            }
        });
    }

    private void initialize(boolean youWin, boolean isBlue) {
        if (isBlue) {
            winnerLabel = new Label("", skin, "blue");
        } else {
            winnerLabel = new Label("", skin, "red");
        }
        if (youWin) {
            winnerLabel.setText("YOU WIN");
        } else {
            winnerLabel.setText("YOU LOSE");
        }
        backToMenuButton = new ToMainMenuButton("BACK TO MENU", skin, menuChangeable, "back");
    }
}
