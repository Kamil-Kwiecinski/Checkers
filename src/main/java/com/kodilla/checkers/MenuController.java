package com.kodilla.checkers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void newGameAction() {
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
    @FXML
    public void rulesAction(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("rulesView.fxml"));
        AnchorPane anchorPane = new AnchorPane();
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
            RulesController rulesController = loader.getController();
            rulesController.setMainController(mainController);
            mainController.setScreen(anchorPane);
    }

    @FXML
    public void quitAction(){
        Platform.exit();
    }
}