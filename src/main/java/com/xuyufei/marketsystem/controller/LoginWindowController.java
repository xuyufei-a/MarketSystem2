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

    final String wrongMessage1 = "用户不存在或密码不正确";
    final String wrongMessage2 = "该用户已被封禁";

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

        int flag = main.login(username, password);
        if(flag == 1) {
            wrongInputPrompt.setText(wrongMessage1);
        } else if(flag == 2) {
            wrongInputPrompt.setText(wrongMessage2);
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
