package ru.edu.kolyanpie.view.menu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.edu.kolyanpie.view.menu.button.ToMainMenuButton;

public class PauseMenuTable extends Table {
    private final MenuChangeable menuChangeable;
    private final Skin skin;
    private final Continuable continuable;
    private Label pauseLabel;
    private TextButton continueButton;
    private TextButton backToMenuButton;

    public PauseMenuTable(Skin skin, MenuChangeable menuChangeable, Continuable continuable) {
        super(skin);
        this.menuChangeable = menuChangeable;
        this.skin = getSkin();
        this.continuable = continuable;
        initialize();
        add(pauseLabel);
        row();
        add(continueButton);
        row();
        add(backToMenuButton);
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.MENU || keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    continuable.unPause();
                    return true;
                }
                return false;
            }
        });
    }

    private void initialize() {
        pauseLabel = new Label("PAUSE", skin, "back");
        continueButton = new TextButton("CONTINUE", skin, "back");
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                continuable.unPause();
            }
        });
        backToMenuButton = new ToMainMenuButton("BACK TO MENU", skin, menuChangeable, "back");
    }
}
