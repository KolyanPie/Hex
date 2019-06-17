package ru.edu.kolyanpie.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

class Cell extends Actor {
    private Field field;
    private State state;
    private static Sprite sprite = new Sprite(new Texture(Gdx.files.internal("cell.png")));

    Cell(Field field) {
        this.field = field;
        initialize();
    }

    static Color getEmptyColor() {
        return State.EMPTY.getColor();
    }

    static void setEmptyColor(Color color) {
        State.EMPTY.setColor(color);
    }

    static Color getBlueColor() {
        return State.BLUE.getColor();
    }

    static void setBlueColor(Color color) {
        State.BLUE.setColor(color);
    }

    static Color getRedColor() {
        return State.RED.getColor();
    }

    static void setRedColor(Color color) {
        State.RED.setColor(color);
    }

    boolean setBlue() {
        if (isEmpty()) {
            state = State.BLUE;
            return true;
        }
        return false;
    }

    boolean setRed() {
        if (isEmpty()) {
            state = State.RED;
            return true;
        }
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.setSize(getWidth(), getHeight());
        sprite.setPosition(getX(), getY());
        sprite.setColor(state.getColor());
        sprite.draw(batch);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        if (x >= 0
                && x <= getWidth() - 1
                && y > 9 * getHeight() / 41
                && y <= 31 * getHeight() / 41
                ) {
            return this;
        }
        for (int i = 0; i < 9; i++) {
            if (x > (17 - 2 * i) * getWidth() / 41
                    && x <= (21 + 2 * i) * getWidth() / 41
                    && (y > i * getHeight() / 41
                    && y <= (1 + i) * getHeight() / 41
                    || y <= getHeight() - 1 - i
                    && y > getHeight() - 1 - (i + 1))
                    ) {
                return this;
            }
        }
        return null;
    }

    private boolean isEmpty() {
        return state.equals(State.EMPTY);
    }

    private void initialize() {
        state = State.EMPTY;
        setTouchable(Touchable.enabled);
        final Cell link = this;
        addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (!isEmpty()) {
                    return;
                }
                field.cellClicked(link);
            }
        });
    }

    private enum State {
        EMPTY(new Color(1, 1, 1, 1)),
        BLUE(new Color(0, 0, 1, 1)),
        RED(new Color(1, 0, 0, 1));

        private Color color;

        private Color getColor() {
            return color;
        }

        private void setColor(Color color) {
            this.color = color;
        }

        State(Color color) {
            this.color = color;
        }
    }
}
