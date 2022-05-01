package com.kodilla.checkers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;

import static java.lang.String.valueOf;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

public class GameController {

    public GameController() {
        initialize();
    }

    private MainController mainController;
    public static Label labelOne;
    public static Label labelTwo;
    public static Label labelXCoordinate;
    public static Label labelYCoordinate;
    public static Label statusLabel;
    public int xCoordinate;
    public int yCoordinate;
    public int newXCoordinate;
    public int newYCoordinate;
    public Game game = new Game();
    public Cell[][] cells = game.createCells();
    public Checker[][] checkers = game.createCheckersOnCells(cells);
    public int rounds = 1;
    public int totalNumberOfCheckers = 24;
    public int totalNumberOfWhiteCheckers;
    public int totalNumberOfBlackCheckers;
    public Node circle;
    String actualColor = "0xffffffff";

    @FXML
    public GridPane gridPane;
    @FXML
    public Label statusText;
    @FXML
    public Label playerOneText;
    @FXML
    public Label playerTwoText;
    @FXML
    public Label statusXCoordinate;
    @FXML
    public Label statusYCoordinate;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void mouseChooseChecker(MouseEvent e) {

        circle = (Node) e.getSource();

        if (circle.toString().contains(actualColor)) {

            Integer columnIndex = GridPane.getColumnIndex(circle);
            Integer rowIndex = GridPane.getRowIndex(circle);

            xCoordinate = columnIndex - 2;
            yCoordinate = Math.abs(rowIndex - 8);
            System.out.println("Mouse entered cell: " + xCoordinate + " " + yCoordinate);

            String numberY = rowIndex.toString();
            statusYCoordinate.setText(numberY);

            if (columnIndex == 2) {
                statusXCoordinate.setText("A");
            } else if (columnIndex == 3) {
                statusXCoordinate.setText("B");
            } else if (columnIndex == 4) {
                statusXCoordinate.setText("C");
            } else if (columnIndex == 5) {
                statusXCoordinate.setText("D");
            } else if (columnIndex == 6) {
                statusXCoordinate.setText("E");
            } else if (columnIndex == 7) {
                statusXCoordinate.setText("F");
            } else if (columnIndex == 8) {
                statusXCoordinate.setText("G");
            } else if (columnIndex == 9) {
                statusXCoordinate.setText("H");
            }
        }
    }

    public void mouseChoosePlace(MouseEvent e) {
        Node source = (Node) e.getSource();
        String choosenCircleColor = "";

        if (source.toString().contains("fill=0x5e5e5eff")) {
            Integer columnIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            newXCoordinate = columnIndex - 2;
            newYCoordinate = Math.abs(rowIndex - 8);
            System.out.println("Mouse choose cell: " + newXCoordinate + " " + newYCoordinate);

            if (circle.toString().contains("0xffffffff")) {
                choosenCircleColor = "white";
            } else if (circle.toString().contains("0x000000ff")) {
                choosenCircleColor = "black";
            }

            if (rounds <= 2
                    && Math.abs(newXCoordinate - xCoordinate) <= 2
                    && Math.abs(newYCoordinate - yCoordinate) <= 2) {
                deleteAndCreateCircles();
                rounds++;

            } else if (rounds > 2) {
                if (checkers[xCoordinate][yCoordinate].isQueen()) {
                    deleteAndCreateCircles();
                    rounds++;
                } else {
                    if ((Math.abs(newXCoordinate - xCoordinate) == 1)
                            && (Math.abs(newYCoordinate - yCoordinate) == 1)) {
                        if (cells[xCoordinate + 1][yCoordinate + 1].isEmpty()) {
                            deleteAndCreateCircles();
                            rounds++;
                        } else if (cells[xCoordinate + 1][yCoordinate - 1].isEmpty()) {
                            deleteAndCreateCircles();
                            rounds++;
                        } else if (cells[xCoordinate - 1][yCoordinate + 1].isEmpty()) {
                            deleteAndCreateCircles();
                            rounds++;
                        } else if (cells[xCoordinate - 1][yCoordinate - 1].isEmpty()) {
                            deleteAndCreateCircles();
                            rounds++;
                        }

                    } else if ((Math.abs(newXCoordinate - xCoordinate) == 2)
                            && (Math.abs(newYCoordinate - yCoordinate) == 2)) {
                        if ((!cells[xCoordinate + 1][yCoordinate + 1].isEmpty())
                                && !checkers[xCoordinate + 1][yCoordinate + 1].getColor().equals(choosenCircleColor)) {
                            deleteAndCreateCircles();
                            rounds++;
                        } else if ((!cells[xCoordinate + 1][yCoordinate - 1].isEmpty())
                                && !checkers[xCoordinate + 1][yCoordinate - 1].getColor().equals(choosenCircleColor)) {
                            deleteAndCreateCircles();
                            rounds++;
                        } else if ((!cells[xCoordinate - 1][yCoordinate + 1].isEmpty())
                                && !checkers[xCoordinate - 1][yCoordinate + 1].getColor().equals(choosenCircleColor)) {
                            deleteAndCreateCircles();
                            rounds++;
                        } else if ((!cells[xCoordinate - 1][yCoordinate - 1].isEmpty())
                                && !checkers[xCoordinate - 1][yCoordinate - 1].getColor().equals(choosenCircleColor)) {
                            deleteAndCreateCircles();
                            rounds++;
                        }
                    }
                }
            }
        }

        totalNumberOfWhiteCheckers = game.howManyColorCheckers(checkers, "white");
        totalNumberOfBlackCheckers = game.howManyColorCheckers(checkers, "black");
        totalNumberOfCheckers = totalNumberOfWhiteCheckers + totalNumberOfBlackCheckers;

        if (totalNumberOfWhiteCheckers == 0) {
            statusText.setText("Blacks won.");
        } else if (totalNumberOfBlackCheckers == 0) {
            statusText.setText("Whites won");
        }

        if (rounds % 2 == 0) {
            statusText.setText("Blacks move.");
            actualColor = "0x000000ff";
        } else {
            statusText.setText("White move.");
            actualColor = "0xffffffff";
        }
    }

    private void deleteAndCreateCircles() {
        checkers = game.move(cells, checkers, xCoordinate, yCoordinate, newXCoordinate,
                newYCoordinate);

        for (int i = 79 + totalNumberOfCheckers; i > 79; i--) {
            gridPane.getChildren().remove(i);
        }

        for (int i = 0; i < 8; i++) {
            for (int n = 0; n < 8; n++) {
                if (checkers[i][n] != null) {
                    System.out.println(checkers[i][n]);
                    createNewCircle(i, n);
                }
            }
        }
    }

    private void createNewCircle(int i, int n) {
        Circle temporaryCircle = new Circle();
        String temporaryId = valueOf(checkers[i][n].getId());
        int temporaryX = checkers[i][n].getCurrentPositionX();
        int temporaryY = checkers[i][n].getCurrentPositionY();
        String temporaryColor = checkers[i][n].getColor();

        temporaryCircle.setId(temporaryId);
        temporaryCircle.setRadius(24.0);
        temporaryCircle.setOnMousePressed(z -> {
            mouseChooseChecker(z);
        });

        if (temporaryColor.equals("white")) {
            temporaryCircle.setFill(WHITE);
            GridPane.setConstraints(temporaryCircle, temporaryX + 2, Math.abs(temporaryY - 8));

        } else if (temporaryColor.equals("black")) {
            temporaryCircle.setFill(BLACK);
            GridPane.setConstraints(temporaryCircle, temporaryX + 2, Math.abs(temporaryY - 8));
        }
        gridPane.getChildren().add(temporaryCircle);
    }

    public void pauseAction(KeyEvent event) {

        Parent root = new AnchorPane();

        if (event.getCode() == KeyCode.ESCAPE) {
            Font applicationFont = new Font("Lucida Bright", 12);
            VBox pauseRoot = new VBox(5);
            Label pauseTitle = new Label();
            pauseTitle.setText("Paused");

            pauseTitle.setFont(new Font("Lucida Bright", 24));
            pauseRoot.getChildren().add(pauseTitle);
            pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 1.0);");
            pauseRoot.setAlignment(Pos.CENTER);
            pauseRoot.setPadding(new Insets(30));

            Button resume = new Button("Resume");
            resume.setFont(applicationFont);
            pauseRoot.getChildren().add(resume);

            Button mainMenu = new Button("Back to Menu");
            mainMenu.setFont(applicationFont);
            pauseRoot.getChildren().add(mainMenu);

            Button exit = new Button("Exit");
            exit.setFont(applicationFont);
            pauseRoot.getChildren().add(exit);

            Stage popupStage = new Stage(StageStyle.TRANSPARENT);
            popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));

            exit.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                Text header = new Text();
                header.setFont(applicationFont);
                header.setText("Do you want to quit?");
                alert.setHeaderText(header.getText());
                ((Button) alert.getDialogPane().lookupButton(ButtonType.YES)).setFont(applicationFont);
                ((Button) alert.getDialogPane().lookupButton(ButtonType.NO)).setFont(applicationFont);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.YES) {
                    Platform.exit();
                }
                alert.show();
            });

            resume.setOnAction(e -> {
                root.setEffect(null);
                popupStage.hide();
            });

            mainMenu.setOnAction(e -> {
                mainController.loadMenuView();
                popupStage.hide();
            });
            popupStage.show();
        }
    }

    public void initialize() {
        statusLabel = statusText;
        labelOne = playerOneText;
        labelTwo = playerTwoText;
        labelXCoordinate = statusXCoordinate;
        labelYCoordinate = statusYCoordinate;
    }
}