package ru.edu.kolyanpie.view.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ru.edu.kolyanpie.view.LocalGameScreen;
import ru.edu.kolyanpie.view.MenuScreen;

public class MainMenuTable extends Table {
    private final MenuScreen menuScreen;
    private final Skin skin;
    private TextButton localButton;
    private TextButton lanButton;
    private TextButton netButton;
    private TextButton aboutButton;
    private TextButton quitButton;

    public MainMenuTable(Skin skin, MenuChangeable menuChangeable) {
        super(skin);
        menuScreen = (MenuScreen) menuChangeable;
        this.skin = getSkin();
        initialize();
        center();
        add(localButton);
        row();
        add(lanButton);
        row();
        add(netButton);
        row();
        add(aboutButton);
        row();
        add(quitButton);
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
                    Gdx.app.exit();
                    return true;
                }
                return false;
            }
        });
    }

    private void initialize() {
        localButton = new TextButton("LOCAL", skin);
        localButton.center();
        localButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.changeScreen(new LocalGameScreen(menuScreen.getHex()));
            }
        });
        lanButton = new TextButton("LAN", skin);
        lanButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.changeMenu(new LanMenuTable(skin, menuScreen));
            }
        });
        netButton = new TextButton("NET", skin);
        netButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.changeMenu(new NetMenuTable(skin, menuScreen));
            }
        });
        aboutButton = new TextButton("ABOUT", skin);
        aboutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menuScreen.changeMenu(new AboutMenuTable(skin, menuScreen));
            }
        });
        quitButton = new TextButton("QUIT", skin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }
}
