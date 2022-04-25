package com.kodilla.checkers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameModeController {
    private MainController mainController;

    @FXML
    public void openPlayer(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("playerVsPlayerView.fxml"));
        AnchorPane anchorPane = new AnchorPane();
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PlayerVsPlayerController playerVsPlayerController = loader.getController();
        playerVsPlayerController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

    @FXML
    public void openComputer(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("playerVsComputerView.fxml"));
        AnchorPane anchorPane = new AnchorPane();
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = loader.getController();
        gameController.setMainController(mainController);
        mainController.setScreen(anchorPane);
    }

    @FXML
    public void backMenu() {
        mainController.loadMenuView();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
