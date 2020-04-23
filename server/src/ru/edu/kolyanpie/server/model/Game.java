package ru.edu.kolyanpie.server.model;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    private String uuid;
    private String history;
    private boolean blueWinner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User blue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User red;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public boolean isBlueWinner() {
        return blueWinner;
    }

    public void setBlueWinner(boolean blueWinner) {
        this.blueWinner = blueWinner;
    }

    public User getBlue() {
        return blue;
    }

    public void setBlue(User blue) {
        this.blue = blue;
    }

    public User getRed() {
        return red;
    }

    public void setRed(User red) {
        this.red = red;
    }
}
