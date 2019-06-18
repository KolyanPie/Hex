package ru.edu.kolyanpie.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

class Cell extends Actor {
    private final Field field;
    private State state;
    private static final Sprite sprite = new Sprite(new Texture(Gdx.files.internal("cell.png")));

    Cell(Field field) {
        this.field = field;
        initialize();
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
        switch (state) {
            case EMPTY:
                sprite.setColor(field.getEmptyColor());
                break;
            case BLUE:
                sprite.setColor(field.getBlueColor());
                break;
            case RED:
                sprite.setColor(field.getRedColor());
                break;
        }
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
}
