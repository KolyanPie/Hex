package ru.edu.kolyanpie.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;

public class Field extends Stage {
    private List<List<Cell>> cells;
    private int size;
    private boolean isLand;
    private Sprite field, red, blue;
    private Color fieldColor, redColor, blueColor;

    public Field() {
        super(new FitViewport(698, 423));
        size = 11;
        initialize();
    }

    public void resize(int width, int height) {
        boolean resize = isLand != height <= width;
        if (resize) {
            isLand = !isLand;
            System.out.println("debug");
            if (isLand) {
                getViewport().setWorldSize(698, 423);
                field = new Sprite(new Texture(Gdx.files.internal("landscape/field.png")));
                red = new Sprite(new Texture(Gdx.files.internal("landscape/red.png")));
                blue = new Sprite(new Texture(Gdx.files.internal("landscape/blue.png")));
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        cells.get(i).get(j).setPosition(30 + i * 40 + j * 20, 361 - j * 34);
                    }
                }
            } else {
                getViewport().setWorldSize(492, 746);
                field = new Sprite(new Texture(Gdx.files.internal("portrait/field.png")));
                red = new Sprite(new Texture(Gdx.files.internal("portrait/red.png")));
                blue = new Sprite(new Texture(Gdx.files.internal("portrait/blue.png")));
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        cells.get(i).get(j).setPosition(227 + i * 20 - j * 20, 693 - i * 34 - j * 34);
                    }
                }
            }
        }
        getViewport().update(width, height, true);
    }

    public Color getEmptyColor() {
        return Cell.getEmptyColor();
    }

    public void setEmptyColor(Color emptyColor) {
        Cell.setEmptyColor(emptyColor);
    }

    public Color getFieldColor() {
        return fieldColor;
    }

    public void setFieldColor(Color fieldColor) {
        this.fieldColor = fieldColor;
    }

    public Color getBlueColor() {
        return blueColor;
    }

    public void setBlueColor(Color blueColor) {
        this.blueColor = blueColor;
        Cell.setBlueColor(blueColor);
    }

    public Color getRedColor() {
        return redColor;
    }

    public void setRedColor(Color redColor) {
        this.redColor = redColor;
        Cell.setRedColor(redColor);
    }

    @Override
    public void draw() {
        Batch batch = getBatch();
        batch.begin();
        field.setColor(fieldColor);
        field.draw(batch);
        blue.setColor(blueColor);
        blue.draw(batch);
        red.setColor(redColor);
        red.draw(batch);
        batch.end();
        super.draw();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    private void initialize() {
        fieldColor = new Color(0, 0, 0, 1);
        redColor = new Color(1, 0, 0, 1);
        blueColor = new Color(0, 0, 1, 1);
        Cell.setBlueColor(blueColor);
        Cell.setRedColor(redColor);
        isLand = true;
        cells = new ArrayList<List<Cell>>(size);
        for (int i = 0; i < size; i++) {
            cells.add(new ArrayList<Cell>(size));
        }
        Cell cell;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cell = new Cell();
                cell.setSize(38, 41);
                cells.get(i).add(cell);
                addActor(cell);
            }
        }
        isLand = Gdx.graphics.getHeight() > Gdx.graphics.getWidth();
    }
}
