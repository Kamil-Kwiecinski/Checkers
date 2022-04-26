package com.kodilla.checkers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    public void initialize(){
        loadMenuView();
    }

    public void loadMenuView() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("menuView.fxml"));
        AnchorPane anchorPane = new AnchorPane();
        try {
            anchorPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(anchorPane);
    }

    public void setScreen(AnchorPane anchorPane) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(anchorPane);
    }
}
