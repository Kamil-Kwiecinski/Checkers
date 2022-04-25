package com.kodilla.checkers;

public class Checker {
    private int id;
    private String color;
    private boolean eliminated;
    private boolean queen;
    private int currentPositionX;
    private int currentPositionY;

    public Checker(int id, String color, boolean eliminated, boolean queen, int currentPositionX, int currentPositionY) {
        this.id = id;
        this.color = color;
        this.eliminated = eliminated;
        this.queen = queen;
        this.currentPositionX = currentPositionX;
        this.currentPositionY = currentPositionY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isEliminated() {
        return eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }

    public boolean isQueen() {
        return queen;
    }

    public void setQueen(boolean queen) {
        this.queen = queen;
    }

    public int getCurrentPositionX() {
        return currentPositionX;
    }

    public void setCurrentPosition(int currentPositionX, int currentPositionY) {
        this.currentPositionX = currentPositionX;
        this.currentPositionY = currentPositionY;
    }

    public int getCurrentPositionY() {
        return currentPositionY;
    }


    @Override
    public String toString() {
        return "Checker{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", eliminated=" + eliminated +
                ", queen=" + queen +
                ", currentPositionX=" + currentPositionX +
                ", getCurrentPositionY=" + currentPositionY +
                '}';
    }
}
