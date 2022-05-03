package com.kodilla.checkers;

public class Cell {
    private boolean isEmpty;
    private int xCoordinate;
    private int yCoordinate;
    private String color;
    private String startPosition;

    public Cell(boolean isEmpty, int xCoordinate, int yCoordinate, String color, String startPosition) {
        this.isEmpty = isEmpty;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.color = color;
        this.startPosition = startPosition;

    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "isEmpty=" + isEmpty +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", color='" + color + '\'' +
                ", startPosition='" + startPosition + '\'' +
                '}';
    }
}
