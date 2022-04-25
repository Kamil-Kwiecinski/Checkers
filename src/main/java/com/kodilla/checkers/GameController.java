package com.kodilla.checkers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.WHITE;

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
        circle = (Node)e.getSource();
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
        } else  if (columnIndex == 9) {
            statusXCoordinate.setText("H");
        }
    }

    public void mouseChoosePlace(MouseEvent e) {
        Node source = (Node)e.getSource();
        if(source.toString().contains("fill=0xffffffff") == false) {
            Integer columnIndex = GridPane.getColumnIndex(source);
            Integer rowIndex = GridPane.getRowIndex(source);
            System.out.println(columnIndex + " " + rowIndex);
            newXCoordinate = columnIndex - 2;
            newYCoordinate = Math.abs(rowIndex - 8);
            System.out.println("Mouse choose cell: " + newYCoordinate + " " + newXCoordinate);
            System.out.println(columnIndex + " " + rowIndex);
            GridPane.setRowIndex(circle, rowIndex);
            GridPane.setColumnIndex(circle, columnIndex);
        }
    }

    @FXML
    public void pauseAction(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("pauseView.fxml"));
        AnchorPane anchorPane = new AnchorPane();
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PauseViewController pauseViewController = loader.getController();
        pauseViewController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

    public void initialize() {
        labelOne = playerOneText;
        labelTwo = playerTwoText;
        labelStatus = statusText;
        labelXCoordinate = statusXCoordinate;
        labelYCoordinate = statusYCoordinate;
    }
}
