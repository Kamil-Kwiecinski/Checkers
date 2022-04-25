package com.kodilla.checkers;

public class EnemyChecker {
    private int enemyCounter;
    private int enemyXPosition;
    private int enemyYPosition;

    public EnemyChecker(int enemyCounter, int enemyXPosition, int enemyYPosition) {
        this.enemyCounter = enemyCounter;
        this.enemyXPosition = enemyXPosition;
        this.enemyYPosition = enemyYPosition;
    }

    public int getEnemyCounter() {
        return enemyCounter;
    }

    public void setEnemyCounter(int enemyCounter) {
        this.enemyCounter = enemyCounter;
    }

    public int getEnemyXPosition() {
        return enemyXPosition;
    }

    public void setEnemyXPosition(int enemyXPosition) {
        this.enemyXPosition = enemyXPosition;
    }

    public int getEnemyYPosition() {
        return enemyYPosition;
    }

    public void setEnemyYPosition(int enemyYPosition) {
        this.enemyYPosition = enemyYPosition;
    }
}
