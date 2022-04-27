package com.kodilla.checkers;

public class Elements {
    private Checker[][] checkers;
    private Checker[][] actualCheckers;
    private Cell[][] cells;
    private int firstRound;

    public Elements(Checker[][] checkers, Cell[][] cells, int firstRound, Checker[][] actualCheckers) {
        this.checkers = checkers;
        this.cells = cells;
        this.firstRound = firstRound;
        this.actualCheckers = actualCheckers;
    }

    public Checker[][] getActualCheckers() {
        return actualCheckers;
    }

    public void setActualCheckers(Checker[][] actualCheckers) {
        this.actualCheckers = actualCheckers;
    }

    public int getFirstRound() {
        return firstRound;
    }

    public void setFirstRound(int firstRound) {
        this.firstRound = firstRound;
    }

    public Checker[][] getCheckers() {
        return checkers;
    }

    public void setCheckers(Checker[][] checkers) {
        this.checkers = checkers;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
