package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginWindowController {
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label wrongInputPrompt;
    private Main main;
    private Stage stage;

    public void setMain(Main main, Stage stage) {
        this.main = main;
        this.stage = stage;
    }

    @FXML
    public void login() {
        String username = usernameInput.getText();
        String password = passwordInput.getText();

        System.out.println(username);
        System.out.println(password);

        if(!main.login(username, password)) {
            wrongInputPrompt.setVisible(true);
        } else {
            System.out.println("success!");
            main.openMainWindow();
        }
    }

    @FXML
    public void exit() {
        stage.close();
    }

    @FXML
    public void register() {
//        main.openRegisterWindow();
    }
}
