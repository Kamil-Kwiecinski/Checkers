package com.kodilla.checkers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PlayerVsComputerController extends GameController{
    private MainController mainController;
    Player playerOne;
    Player computer;

    @FXML
    private TextField playerOneTextField;


    @FXML
    public void game(){
        playerOne = new Player(playerOneTextField.getText(), "white");
        computer = new Player("Computer", "black");

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("gameView.fxml"));
        AnchorPane anchorPane = new AnchorPane();
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = loader.getController();
        gameController.setMainController(mainController);
        mainController.setScreen(anchorPane);

        labelOne.setText(playerOneTextField.getText());
        labelTwo.setText(computer.getName());
    }

    @FXML
    public void backGameModeMenu() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("gameModeView.fxml"));
        AnchorPane anchorPane = new AnchorPane();
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameModeController gameModeController = loader.getController();
        gameModeController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
