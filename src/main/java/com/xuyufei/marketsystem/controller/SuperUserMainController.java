package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import com.xuyufei.marketsystem.model.User;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class SuperUserMainController {
    private User user;
    private Stage stage;
    private Main main;

    public void init(Stage stage, Main main, User user) {
        this.stage = stage;
        this.main = main;
        this.user = user;
    }

    @FXML
    public void exit() {
        stage.close();
    }

    @FXML
    public void banMode() {
        main.openSuperBanWindow();
    }

    @FXML
    public void takeDownMode() {
//        main.openSuperTakeDownMode();
    }

    @FXML
    public void discountMode() {
//        main.openSuperDiscountMode();
    }
}
