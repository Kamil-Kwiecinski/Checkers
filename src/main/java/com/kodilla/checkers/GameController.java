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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Optional;

public class GameController {

    private MainController mainController;
    public static Label labelOne;
    public static Label labelTwo;
    public static Label labelStatus;
    public static Label labelXCoordinate;
    public static Label labelYCoordinate;
    public int xCoordinate;
    public int yCoordinate;
    public int newXCoordinate;
    public int newYCoordinate;
    public Node circle;
    public Game game = new Game();

    @FXML
    public Label playerOneText;
    @FXML
    public Label playerTwoText;
    @FXML
    public Label statusText;
    @FXML
    public Label statusXCoordinate;
    @FXML
    public Label statusYCoordinate;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void mouseChooseChecker(MouseEvent e) {

        circle = (Node) e.getSource();
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

    public void mouseChoosePlace(MouseEvent e) {
        Node source = (Node) e.getSource();
        if (source.toString().contains("fill=0xffffffff") == false) {
            Integer columnIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            System.out.println(columnIndex + " " + rowIndex);
            newXCoordinate = columnIndex - 2;
            newYCoordinate = Math.abs(rowIndex - 8);
            System.out.println("Mouse choose cell: " + newYCoordinate + " " + newXCoordinate);
            System.out.println(columnIndex + " " + rowIndex);
            GridPane.setRowIndex(circle, rowIndex);
            GridPane.setColumnIndex(circle, columnIndex);
            game.init(xCoordinate, yCoordinate, newXCoordinate, newYCoordinate);
        }
    }

    public void pauseAction(KeyEvent event) {

        Parent root = new AnchorPane();

        if(event.getCode()==KeyCode.ESCAPE) {
            Font applicationFont = new Font("Lucida Bright", 12);
            VBox pauseRoot = new VBox(5);
            Label pauseTitle = new Label();
            pauseTitle.setText("Paused");

            pauseTitle.setFont(new Font("Lucida Bright", 24) );
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

            exit.setOnAction(e ->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
                Text header = new Text();
                header.setFont(applicationFont);
                header.setText("Do you want to quit?");
                alert.setHeaderText(header.getText());
                ((Button) alert.getDialogPane().lookupButton(ButtonType.YES)).setFont(applicationFont);
                ((Button) alert.getDialogPane().lookupButton(ButtonType.NO)).setFont(applicationFont);

                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.YES)
                {
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
        labelOne = playerOneText;
        labelTwo = playerTwoText;
        labelStatus = statusText;
        labelXCoordinate = statusXCoordinate;
        labelYCoordinate = statusYCoordinate;
    }

}