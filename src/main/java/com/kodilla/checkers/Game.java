package com.kodilla.checkers;


public class Game {

    private static final String WHITE_COLOR = "white";
    private static final String BLACK_COLOR = "black";

    public void init(Game game) {

        Cell[][] cells;
        Checker[][] checkers;
        Checker[][] actualCheckers;

        String[][] table = new String[8][8];
        Board board = new Board(table, false);

        cells = game.createCells();
        checkers = game.createCheckersOnCells(cells);
        board.setReady(true);
        actualCheckers = checkers;
        int firstRound = 1;
    }

    public void round(Game game, Cell[][] cells, Checker[][] checkers, Checker[][] actualCheckers, int firstRound,
                      int oldX, int oldY, int newX, int newY) {

//        boolean end = false;
//        boolean isTrue = false;

//        while(!end) {
//            labelStatus.setText("Whites move.");
            int xCoordinate = oldX;
            int yCoordinate = oldY;
            int newXCoordinate = newX;
            int newYCoordinate = newY;


//            while(!isTrue) {
                if (actualCheckers[xCoordinate][yCoordinate].getColor().equals(WHITE_COLOR)) {
                    actualCheckers = game.move(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                            newYCoordinate, firstRound);
//                    isTrue = true;
//                } else {
//                    System.out.println("It's not your checker.");
                }
//            }

            for (int i = 0; i < 8; i++) {
                for (int n = 0; n < 8; n++) {
                    if(actualCheckers[i][n]!=null) {
                        System.out.println(actualCheckers[i][n]);
                    }
                }
            }

//            labelStatus.setText("Blacks move.");
//            xCoordinate = gameController.xCoordinate;
//            yCoordinate = gameController.yCoordinate;
//            newXCoordinate = gameController.newXCoordinate;
//            newYCoordinate = gameController.newYCoordinate;

//            while(!isTrue) {
                if (actualCheckers[xCoordinate][yCoordinate].getColor().equals(BLACK_COLOR)) {
                    actualCheckers = game.move(cells, actualCheckers, xCoordinate, yCoordinate, newXCoordinate,
                            newYCoordinate, firstRound);
//                    isTrue = true;
//                } else {
//                    System.out.println("It's not your checker.");
                }
//            }

            firstRound = 0;
            System.out.println(game.howManyColorCheckers(actualCheckers, WHITE_COLOR));
            System.out.println(game.howManyColorCheckers(actualCheckers, BLACK_COLOR));

            for (int i = 0; i < 8; i++) {
                for (int n = 0; n < 8; n++) {
                    if(actualCheckers[i][n]!=null) {
                        System.out.println(actualCheckers[i][n]);
                    }
                }
            }

            if (game.howManyColorCheckers(actualCheckers, WHITE_COLOR) == 0) {
                System.out.println("Whites lose.");
            } else if (game.howManyColorCheckers(actualCheckers, BLACK_COLOR) == 0) {
                System.out.println("Blacks lose.");
            }
        }
//    }

    public Cell[][] createCells() {
        Cell[][] cells = new Cell[8][8];
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x = x + 2) {
                cells[x][y] = new Cell(true, x, y, BLACK_COLOR, "");
            }
        }
        for (int y = 0; y < 8; y++) {
            for (int x = 1; x < 8; x = x + 2) {
                cells[x][y] = new Cell(true, x, y, WHITE_COLOR, "");
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0)
                    cells[x][y].setStartPosition(WHITE_COLOR);
            }
        }
        for (int y = 5; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if ((x + y) % 2 == 0)
                    cells[x][y].setStartPosition(BLACK_COLOR);
            }
        }
        System.out.println("Cells are ready");
        return cells;
    }

    public Checker[][] createCheckersOnCells(Cell[][] cells) {
        Checker[][] checkers = new Checker[8][8];
        int counter = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (cells[x][y].getStartPosition().equals(WHITE_COLOR)) {
                    counter++;
                    checkers[x][y] = new Checker(counter, WHITE_COLOR, false, false, x, y);
                    cells[x][y].setEmpty(false);
                }
            }
        }

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
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
                            int newYCoordinate,int firstRound) {

        Game game = new Game();
        Checker[][] actualCheckers = new Checker[8][8];
//        Checker[][] temporaryCheckers = new Checker[8][8];
        EnemyChecker enemyChecker = new EnemyChecker(0,0,0);
        int enemyCheckerCounter = 0;
        int friendlyCheckerCounter = 0;

        if (firstRound == 1) {
            if (Math.abs(newXCoordinate - xCoordinate) <= 2) {
                if (Math.abs(newYCoordinate - yCoordinate) <= 2) {
                  actualCheckers = newCoordinatesIsEmpty(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                          newYCoordinate, game, actualCheckers, enemyChecker, enemyCheckerCounter,
                          friendlyCheckerCounter);
                }
            }
        } else if (firstRound == 0) {
            for (int i = 0; i < 8; i++) {
                for (int n = 0; n < 8; n++) {
                    if (actualCheckers[i][n].isQueen() == true) {
                        actualCheckers = newCoordinatesIsEmpty(cells, checkers, xCoordinate, yCoordinate,
                                newXCoordinate, newYCoordinate, game, actualCheckers, enemyChecker, enemyCheckerCounter,
                                friendlyCheckerCounter);
                    }
                }
            }
        } else if (Math.abs(newXCoordinate - xCoordinate) <= 3) {
            if (Math.abs(newYCoordinate - yCoordinate) <= 3) {
                if (enemyCheckerCounter > 0) {
                    actualCheckers = newCoordinatesIsEmpty(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                            newYCoordinate, game, actualCheckers, enemyChecker, enemyCheckerCounter,
                            friendlyCheckerCounter);
                }
            }
        } else if (Math.abs(newXCoordinate - xCoordinate) <= 1) {
            if (Math.abs(newYCoordinate - yCoordinate) <= 1) {
                actualCheckers = newCoordinatesIsEmpty(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, game, actualCheckers, enemyChecker, enemyCheckerCounter,
                        friendlyCheckerCounter);
            }
        }
        return actualCheckers;
    }

    private Checker[][] newCoordinatesIsEmpty(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                              int newXCoordinate, int newYCoordinate, Game game,
                                              Checker[][] actualCheckers, EnemyChecker enemyChecker,
                                              int enemyCheckerCounter, int friendlyCheckerCounter) {
        if (cells[newXCoordinate][newYCoordinate].isEmpty()) {
            if (newXCoordinate > xCoordinate && newYCoordinate > yCoordinate) {
                actualCheckers = directionXPlusYPlus(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, game, actualCheckers, enemyChecker, enemyCheckerCounter,
                        friendlyCheckerCounter);
            } else if (newXCoordinate > xCoordinate && newYCoordinate < yCoordinate) {
                actualCheckers = directionXPlusYMinus(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, game, actualCheckers, enemyChecker, enemyCheckerCounter,
                        friendlyCheckerCounter);
            } else if (newXCoordinate < xCoordinate && newYCoordinate > yCoordinate) {
                actualCheckers = directionXMinusYPlus(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, game, actualCheckers, enemyChecker, enemyCheckerCounter,
                        friendlyCheckerCounter);
            } else if (newXCoordinate < xCoordinate && newYCoordinate < yCoordinate) {
                actualCheckers = directionXMinusYMinus(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                        newYCoordinate, game, actualCheckers, enemyChecker, enemyCheckerCounter,
                        friendlyCheckerCounter);
            }
        }
        return actualCheckers;
    }

    private Checker[][] directionXMinusYMinus(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                              int newXCoordinate, int newYCoordinate, Game game,
                                              Checker[][] actualCheckers, EnemyChecker enemyChecker,
                                              int enemyCheckerCounter, int friendlyCheckerCounter) {
        for (int i = xCoordinate - 1; i > newXCoordinate; i--) {
            for (int n = yCoordinate - 1; n > newYCoordinate; n--) {
                if (cells[i][n].isEmpty() == false) {
                    if (checkers[i][n] != null) {
                        if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true) {
                            friendlyCheckerCounter++;
                        } else if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) ==
                                false) {
                            enemyChecker = prepareEnemyCheckerValues(enemyChecker, enemyCheckerCounter, i, n);
                        }
                    }
                }
            }
        }
        actualCheckers = whoIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate, newYCoordinate, game,
                actualCheckers, enemyChecker, friendlyCheckerCounter);
        return actualCheckers;
    }

    private Checker[][] directionXMinusYPlus(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                             int newXCoordinate, int newYCoordinate, Game game,
                                             Checker[][] actualCheckers, EnemyChecker enemyChecker,
                                             int enemyCheckerCounter, int friendlyCheckerCounter) {
        for (int i = xCoordinate - 1; i > newXCoordinate; i--) {
            for (int n = yCoordinate + 1; n < newYCoordinate; n++) {
                if (cells[i][n].isEmpty() == false) {
                    if (checkers[i][n] != null) {
                        if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true) {
                            friendlyCheckerCounter++;
                        } else if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) ==
                                false) {
                            enemyChecker = prepareEnemyCheckerValues(enemyChecker, enemyCheckerCounter, i, n);
                        }
                    }
                }
            }
        }
        actualCheckers = whoIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate, newYCoordinate, game,
                actualCheckers, enemyChecker, friendlyCheckerCounter);
        return actualCheckers;
    }

    private Checker[][] directionXPlusYMinus(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                             int newXCoordinate, int newYCoordinate, Game game,
                                             Checker[][] actualCheckers, EnemyChecker enemyChecker,
                                             int enemyCheckerCounter, int friendlyCheckerCounter) {
        for (int i = xCoordinate + 1; i < newXCoordinate; i++) {
            for (int n = yCoordinate - 1; n > newYCoordinate; n--) {
                if (cells[i][n].isEmpty() == false) {
                    if (checkers[i][n] != null) {
                        if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true) {
                            friendlyCheckerCounter++;
                        } else if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) ==
                                false) {
                            enemyChecker = prepareEnemyCheckerValues(enemyChecker, enemyCheckerCounter, i, n);
                        }
                    }
                }
            }
        }
        actualCheckers = whoIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate, newYCoordinate, game,
                actualCheckers, enemyChecker, friendlyCheckerCounter);
        return actualCheckers;
    }

    private Checker[][] directionXPlusYPlus(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                            int newXCoordinate, int newYCoordinate, Game game,
                                            Checker[][] actualCheckers, EnemyChecker enemyChecker,
                                            int enemyCheckerCounter, int friendlyCheckerCounter) {
        for (int i = xCoordinate + 1; i < newXCoordinate; i++) {
            for (int n = yCoordinate + 1; n < newYCoordinate; n++) {
                if (cells[i][n].isEmpty() == false) {
                    if (checkers[i][n] != null) {
                        if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) == true) {
                            friendlyCheckerCounter++;
                        } else if (checkers[i][n].getColor().equals(checkers[xCoordinate][yCoordinate].getColor()) ==
                                false) {
                            enemyChecker = prepareEnemyCheckerValues(enemyChecker, enemyCheckerCounter, i, n);
                        }
                    }
                }
            }
        }
        actualCheckers = whoIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate, newYCoordinate, game,
                actualCheckers, enemyChecker, friendlyCheckerCounter);
        return actualCheckers;
    }

    private Checker[][] whoIsClose(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                   int newXCoordinate, int newYCoordinate, Game game, Checker[][] actualCheckers,
                                   EnemyChecker enemyChecker, int friendlyCheckerCounter) {
        if(enemyChecker.getEnemyCounter() == 0) {
            if(friendlyCheckerCounter == 1) {
                actualCheckers = checkers;
            } else {
                actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate,
                        checkers);
            }
        } else if (enemyChecker.getEnemyCounter() == 1) {
            actualCheckers = enemyCheckerIsClose(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                    newYCoordinate, game, enemyChecker);
        }
        return actualCheckers;
    }

    private Checker[][] enemyCheckerIsClose(Cell[][] cells, Checker[][] checkers, int xCoordinate, int yCoordinate,
                                            int newXCoordinate, int newYCoordinate, Game game,
                                            EnemyChecker enemyChecker) {
        Checker[][] actualCheckers;
        Checker[][] temporaryCheckers;
        int enemyXPosition = enemyChecker.getEnemyXPosition();
        int enemyYPosition = enemyChecker.getEnemyYPosition();
        temporaryCheckers = game.elimination(enemyXPosition, enemyYPosition, cells, checkers);
        int howManyWhiteCheckers = game.howManyColorCheckers(temporaryCheckers, WHITE_COLOR);
        int howManyBlackCheckers = game.howManyColorCheckers(temporaryCheckers, BLACK_COLOR);

        if(howManyWhiteCheckers == 1) {
            actualCheckers = game.findLastOne(temporaryCheckers, WHITE_COLOR);
        } else if(howManyBlackCheckers == 1) {
            actualCheckers = game.findLastOne(temporaryCheckers, BLACK_COLOR);
        } else {
            actualCheckers = game.newPosition(cells, newXCoordinate, newYCoordinate, xCoordinate, yCoordinate,
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
        Checker[][] actualCheckers = new Checker[8][8];

        int temporaryId = checkers[xCoordinate][yCoordinate].getId();
        String temporaryColor = checkers[xCoordinate][yCoordinate].getColor();
        boolean temporaryEliminated = checkers[xCoordinate][yCoordinate].isEliminated();
        boolean temporaryQueen = checkers[xCoordinate][yCoordinate].isQueen();
        Checker temporaryChecker = new Checker(temporaryId, temporaryColor, temporaryEliminated, temporaryQueen,
                newXCoordinate, newYCoordinate);

        cells[newXCoordinate][newYCoordinate].setEmpty(false);
        cells[xCoordinate][yCoordinate].setEmpty(true);

        actualCheckers[newXCoordinate][newYCoordinate] = temporaryChecker;

        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                if(checkers[x][y] != checkers[xCoordinate][yCoordinate]) {
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
        return actualCheckers;
    }

    public Checker[][] elimination(int xCoordinate, int yCoordinate, Cell cells[][], Checker[][] checkers) {
        Checker[][] actualCheckers = new Checker[8][8];
        cells[xCoordinate][yCoordinate].setEmpty(true);
        checkers[xCoordinate][yCoordinate].setEliminated(true);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
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
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if(checkers[x][y] != null) {
                    if(checkers[x][y].getColor() == color) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    public Checker[][] findLastOne(Checker[][] checkers, String color){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (checkers[x][y] != null) {
                    if (checkers[x][y].getColor() == color) {
                        checkers[x][y].setQueen(true);
                    }
                }
            }
        }
        return  checkers;
    }
}