package com.kodilla.checkers;

public class Board {
    private String boardOfCheckers[][];
    private boolean isReady;

    public Board(String[][] boardOfCheckers, boolean isReady) {
        this.boardOfCheckers = boardOfCheckers;
        this.isReady = isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
