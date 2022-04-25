package com.kodilla.checkers;

import javafx.fxml.FXML;

public class RulesController {
    private MainController mainController;

    @FXML
    public void backMenu(){
        mainController.loadMenuView();
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
}
