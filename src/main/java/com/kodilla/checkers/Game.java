package com.kodilla.checkers;


public class Game {

    private static final String WHITE_COLOR = "white";
    private static final String BLACK_COLOR = "black";
    private static final int sizeOfBoard = 8;

    public Cell[][] createCells() {
        Cell[][] cells = new Cell[sizeOfBoard][sizeOfBoard];
        for (int y = 0; y < sizeOfBoard; y++) {
            for (int x = 0; x < sizeOfBoard; x = x + 2) {
                cells[x][y] = new Cell(true, x, y, BLACK_COLOR, "");
            }
        }
        for (int y = 0; y < sizeOfBoard; y++) {
            for (int x = 1; x < sizeOfBoard; x = x + 2) {
                cells[x][y] = new Cell(true, x, y, WHITE_COLOR, "");
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < sizeOfBoard; x++) {
                if ((x + y) % 2 == 0)
                    cells[x][y].setStartPosition(WHITE_COLOR);
            }
        }
        for (int y = 5; y < sizeOfBoard; y++) {
            for (int x = 0; x < sizeOfBoard; x++) {
                if ((x + y) % 2 == 0)
                    cells[x][y].setStartPosition(BLACK_COLOR);
            }
        }
        System.out.println("Cells are ready");
        return cells;
    }

    public Checker[][] createCheckersOnCells(Cell[][] cells) {
        Checker[][] checkers = new Checker[sizeOfBoard][sizeOfBoard];
        int counter = 0;
        for (int y = 0; y < sizeOfBoard; y++) {
            for (int x = 0; x < sizeOfBoard; x++) {
                if (cells[x][y].getStartPosition().equals(WHITE_COLOR)) {
                    counter++;
                    checkers[x][y] = new Checker(counter, WHITE_COLOR, false, false, x, y);
                    cells[x][y].setEmpty(false);
                }
            }
        }

        for (int y = 0; y < sizeOfBoard; y++) {
            for (int x = 0; x < sizeOfBoard; x++) {
                if (cells[x][y].getStartPosition().equals(BLACK_COLOR)) {
                    counter++;
                    checkers[x][y] = new Checker(counter, BLACK_COLOR, false, false, x, y);
                    cells[x][y].setEmpty(false);
                }
            }
        }
        System.out.println("Checkers on cells");
        return checkers;
    }

    public Checker[][] move(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate, int newXCoordinate,
                            int newYCoordinate) {

        EnemyChecker enemyChecker = new EnemyChecker(0,0,0);
        int friendlyCheckerCounter = 0;
        int enemyCheckerCounter = 0;

        checkers = newCoordinatesIsEmpty(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                newYCoordinate, enemyChecker, enemyCheckerCounter, friendlyCheckerCounter);

        return checkers;
    }

    private Checker[][] newCoordinatesIsEmpty(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                              int newXCoordinate, int newYCoordinate, EnemyChecker enemyChecker,
                                              int enemyCheckerCounter, int friendlyCheckerCounter) {

        if (cells[newXCoordinate][newYCoordinate].isEmpty()) {
            if (newXCoordinate > xCoordinate && newYCoordinate > yCoordinate) {
                checkers = directionXPlusYPlus(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, enemyChecker, enemyCheckerCounter, friendlyCheckerCounter);
            } else if (newXCoordinate > xCoordinate && newYCoordinate < yCoordinate) {
                checkers = directionXPlusYMinus(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, enemyChecker, enemyCheckerCounter, friendlyCheckerCounter);
            } else if (newXCoordinate < xCoordinate && newYCoordinate > yCoordinate) {
                checkers = directionXMinusYPlus(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, enemyChecker, enemyCheckerCounter, friendlyCheckerCounter);
            } else if (newXCoordinate < xCoordinate && newYCoordinate < yCoordinate) {
                checkers = directionXMinusYMinus(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, enemyChecker, enemyCheckerCounter, friendlyCheckerCounter);
            }
        }
        return checkers;
    }

    private Checker[][] directionXMinusYMinus(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                              int newXCoordinate, int newYCoordinate, EnemyChecker enemyChecker,
                                              int enemyCheckerCounter, int friendlyCheckerCounter) {

        for (int i = xCoordinate - 1; i > newXCoordinate; i--) {
            for (int n = yCoordinate - 1; n > newYCoordinate; n--) {
                if (!cells[i][n].isEmpty()) {
                    if (checkers[i][n] != null) {
                        if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor())) {
                            friendlyCheckerCounter++;
                        } else if (!checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor())) {
                            enemyChecker = prepareEnemyCheckerValues(enemyChecker, enemyCheckerCounter, i, n);
                        }
                    }
                }
            }
        }
        checkers = whoIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate, newYCoordinate,
                enemyChecker, friendlyCheckerCounter);
        return checkers;
    }

    private Checker[][] directionXMinusYPlus(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                             int newXCoordinate, int newYCoordinate,
                                             EnemyChecker enemyChecker, int enemyCheckerCounter,
                                             int friendlyCheckerCounter) {

        for (int i = xCoordinate - 1; i > newXCoordinate; i--) {
            for (int n = yCoordinate + 1; n < newYCoordinate; n++) {
                if (!cells[i][n].isEmpty()) {
                    if (checkers[i][n] != null) {
                        if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor())) {
                            friendlyCheckerCounter++;
                        } else if (!checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor())) {
                            enemyChecker = prepareEnemyCheckerValues(enemyChecker, enemyCheckerCounter, i, n);
                        }
                    }
                }
            }
        }
        checkers = whoIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate, newYCoordinate, enemyChecker,
                friendlyCheckerCounter);
        return checkers;
    }

    private Checker[][] directionXPlusYMinus(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                             int newXCoordinate, int newYCoordinate, EnemyChecker enemyChecker,
                                             int enemyCheckerCounter, int friendlyCheckerCounter) {

        for (int i = xCoordinate + 1; i < newXCoordinate; i++) {
            for (int n = yCoordinate - 1; n > newYCoordinate; n--) {
                if (!cells[i][n].isEmpty()) {
                    if (checkers[i][n] != null) {
                        if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor())) {
                            friendlyCheckerCounter++;
                        } else if (!checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor())) {
                            enemyChecker = prepareEnemyCheckerValues(enemyChecker, enemyCheckerCounter, i, n);
                        }
                    }
                }
            }
        }
        checkers = whoIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate, newYCoordinate, enemyChecker,
                friendlyCheckerCounter);
        return checkers;
    }

    private Checker[][] directionXPlusYPlus(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                            int newXCoordinate, int newYCoordinate, EnemyChecker enemyChecker,
                                            int enemyCheckerCounter, int friendlyCheckerCounter) {

        for (int i = xCoordinate + 1; i < newXCoordinate; i++) {
            for (int n = yCoordinate + 1; n < newYCoordinate; n++) {
                if (!cells[i][n].isEmpty()) {
                    if (checkers[i][n] != null) {
                        if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor())) {
                            friendlyCheckerCounter++;
                        } else if (!checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor())) {
                            enemyChecker = prepareEnemyCheckerValues(enemyChecker, enemyCheckerCounter, i, n);
                        }
                    }
                }
            }
        }
        checkers = whoIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate, newYCoordinate, enemyChecker,
                friendlyCheckerCounter);
        return checkers;
    }

    private Checker[][] whoIsClose(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                   int newXCoordinate, int newYCoordinate, EnemyChecker enemyChecker,
                                   int friendlyCheckerCounter) {

        Checker[][] actualCheckers = new Checker[sizeOfBoard][sizeOfBoard];
        if(enemyChecker.getEnemyCounter() == 0) {
            if(friendlyCheckerCounter == 1) {
                actualCheckers = checkers;
            } else {
                actualCheckers = this.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate,
                        checkers);
            }
        } else if (enemyChecker.getEnemyCounter() == 1) {
            actualCheckers = enemyCheckerIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                    newYCoordinate, enemyChecker);
        }
        return actualCheckers;
    }

    private Checker[][] enemyCheckerIsClose(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                            int newXCoordinate, int newYCoordinate, EnemyChecker enemyChecker) {

        Checker[][] actualCheckers;
        Checker[][] temporaryCheckers;
        int enemyXPosition = enemyChecker.getEnemyXPosition();
        int enemyYPosition = enemyChecker.getEnemyYPosition();
        temporaryCheckers = this.elimination(enemyXPosition, enemyYPosition, cells, checkers);
        int howManyWhiteCheckers = this.howManyColorCheckers(temporaryCheckers, WHITE_COLOR);
        int howManyBlackCheckers = this.howManyColorCheckers(temporaryCheckers, BLACK_COLOR);

        if (howManyWhiteCheckers == 1) {
            actualCheckers = this.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate,
                    temporaryCheckers);
            actualCheckers = this.findLastOne(actualCheckers, WHITE_COLOR);

        } else if(howManyBlackCheckers == 1) {
            actualCheckers = this.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate,
                    temporaryCheckers);
            actualCheckers = this.findLastOne(actualCheckers, BLACK_COLOR);

        } else {
            actualCheckers = this.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate,
                    temporaryCheckers);
        }
        return actualCheckers;
    }

    private EnemyChecker prepareEnemyCheckerValues(EnemyChecker enemyChecker, int enemyCheckerCounter, int i, int n) {
        enemyCheckerCounter++;
        enemyChecker.setEnemyCounter(enemyCheckerCounter);
        enemyChecker.setEnemyXPosition(i);
        enemyChecker.setEnemyYPosition(n);
        return enemyChecker;
    }

    public Checker[][] newPosition(Cell cells[][], int newXCoordinate, int newYCoordinate, int xCoordinate,
                                   int yCoordinate, Checker[][] checkers) {

        Checker[][] actualCheckers = new Checker[sizeOfBoard][sizeOfBoard];

        if (checkers[xCoordinate][yCoordinate] != null) {
            int temporaryId = checkers[xCoordinate][yCoordinate].getId();
            String temporaryColor = checkers[xCoordinate][yCoordinate].getColor();
            boolean temporaryEliminated = checkers[xCoordinate][yCoordinate].isEliminated();
            boolean temporaryQueen = checkers[xCoordinate][yCoordinate].isQueen();
            Checker temporaryChecker = new Checker(temporaryId, temporaryColor, temporaryEliminated, temporaryQueen,
                    newXCoordinate, newYCoordinate);

            cells[newXCoordinate][newYCoordinate].setEmpty(false);
            cells[xCoordinate][yCoordinate].setEmpty(true);

            actualCheckers[newXCoordinate][newYCoordinate] = temporaryChecker;

            for (int y = 0; y < sizeOfBoard; y++) {
                for (int x = 0; x < sizeOfBoard; x++) {
                    if (checkers[x][y] != checkers[xCoordinate][yCoordinate]) {
                        if (checkers[x][y] != null) {
                            temporaryId = checkers[x][y].getId();
                            temporaryColor = checkers[x][y].getColor();
                            temporaryEliminated = checkers[x][y].isEliminated();
                            temporaryQueen = checkers[x][y].isQueen();
                            temporaryChecker = new Checker(temporaryId, temporaryColor, temporaryEliminated, temporaryQueen,
                                    x, y);
                            actualCheckers[x][y] = temporaryChecker;
                        }
                    }
                }
            }
        }
        return actualCheckers;
    }

    public Checker[][] elimination(int xCoordinate, int yCoordinate, Cell cells[][], Checker[][] checkers) {
        Checker[][] actualCheckers = new Checker[sizeOfBoard][sizeOfBoard];
        cells[xCoordinate][yCoordinate].setEmpty(true);
        checkers[xCoordinate][yCoordinate].setEliminated(true);

        for (int y = 0; y < sizeOfBoard; y++) {
            for (int x = 0; x < sizeOfBoard; x++) {
                if (checkers[x][y] != checkers[xCoordinate][yCoordinate]) {
                    if (checkers[x][y] != null) {
                        actualCheckers[x][y] = checkers[x][y];
                    }
                }
            }
        }
        return actualCheckers;
    }

    public int howManyColorCheckers(Checker[][] checkers, String color) {
        int counter = 0;
        for (int x = 0; x < sizeOfBoard; x++) {
            for (int y = 0; y < sizeOfBoard; y++) {
                if(checkers[x][y] != null) {
                    if(checkers[x][y].getColor().equals(color)) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    public Checker[][] findLastOne(Checker[][] checkers, String color){
        for (int x = 0; x < sizeOfBoard; x++) {
            for (int y = 0; y < sizeOfBoard; y++) {
                if (checkers[x][y] != null) {
                    if (checkers[x][y].getColor().equals(color)) {
                        checkers[x][y].setQueen(true);
                    }
                }
            }
        }
        return  checkers;
    }
}