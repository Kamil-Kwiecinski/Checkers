package com.kodilla.checkers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Optional;

public class PauseViewController {
    private MainController mainController;

    public static Optional<ButtonType> areYouSureToQuit() {
        Alert exitAlert = new Alert(Alert.AlertType.INFORMATION);
        exitAlert.setTitle("Exit");
        exitAlert.setHeaderText("Confirmation");
        Optional<ButtonType> result = exitAlert.showAndWait();
        return result;

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

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }

    @FXML
    public void backMenu() {
        mainController.loadMenuView();
    }

}
