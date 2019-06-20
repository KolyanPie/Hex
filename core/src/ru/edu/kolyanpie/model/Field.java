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

import ru.edu.kolyanpie.controller.CellClickListener;

public class Field extends Stage {
    private List<List<Cell>> cells;
    private final int size;
    private boolean isLand;
    private Sprite field, red, blue;
    private Color emptyColor, fieldColor, blueColor, redColor;
    private final CellClickListener cellClickListener;

    public Field(CellClickListener cellClickListener) {
        super(new FitViewport(698, 423));
        this.cellClickListener = cellClickListener;
        size = 11;
        cells = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            cells.add(new ArrayList<>(size));
        }
        Cell cell;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cell = new Cell(this);
                cell.setSize(38, 41);
                cells.get(i).add(cell);
                addActor(cell);
            }
        }
        isLand = Gdx.graphics.getHeight() > Gdx.graphics.getWidth();
        initialize();
    }

    public Field(CellClickListener cellClickListener, List<List<State>> cells) {
        this(cellClickListener);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (cells.get(i).get(j)) {
                    case EMPTY:
                        break;
                    case BLUE:
                        this.cells.get(i).get(j).setBlue();
                        break;
                    case RED:
                        this.cells.get(i).get(j).setRed();
                        break;
                }
             }
        }
    }

    public void resize(int width, int height) {
        boolean resize = isLand != height <= width;
        if (resize) {
            isLand = !isLand;
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

    public boolean setBlue(int i, int j) {
        return cells.get(i).get(j).setBlue();
    }

    public boolean setRed(int i, int j) {
        return cells.get(i).get(j).setRed();
    }

    public Color getEmptyColor() {
        return emptyColor;
    }

    public void setEmptyColor(Color emptyColor) {
        this.emptyColor = new Color(emptyColor);
    }

    public Color getFieldColor() {
        return fieldColor;
    }

    public void setFieldColor(Color fieldColor) {
        this.fieldColor = new Color(fieldColor);
    }

    public Color getBlueColor() {
        return blueColor;
    }

    public void setBlueColor(Color blueColor) {
        this.blueColor = new Color(blueColor);
    }

    public Color getRedColor() {
        return redColor;
    }

    public void setRedColor(Color redColor) {
        this.redColor = new Color(redColor);
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

    void cellClicked(Cell cell) {
        int i, j;
        if (isLand) {
            j = (int) ((361 - cell.getY()) / 34);
            i = (int) ((cell.getX() - 30 - j * 20) / 40);
        } else {
            j = (int) ((10789 - 17 * cell.getX() - 10 * cell.getY()) / 680);
            i = (int) ((cell.getX() - 227 + j * 20) / 20);
        }
        cellClickListener.cellClicked(i, j);
    }

    private void initialize() {
        fieldColor = new Color(0, 0, 0, 1);
        emptyColor = new Color(1, 1, 1, 1);
        blueColor = new Color(0, 0, 1, 1);
        redColor = new Color(1, 0, 0, 1);
    }
}
