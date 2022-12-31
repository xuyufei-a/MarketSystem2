package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import com.xuyufei.marketsystem.model.User;
import com.xuyufei.marketsystem.repo.UserRepo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterWindowController {
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label wrongInputPrompt;

    private Main main;
    private Stage stage;

    final String wrongMessage = "用户名已被占用";

    public void setMain(Main main, Stage stage) {
        this.main = main;
        this.stage = stage;
    }

    public void registerComUser() {
        String name = usernameInput.getText();
        String password = passwordInput.getText();

        UserRepo userRepo = new UserRepo();
        if(userRepo.contain(name)) {
            wrongInputPrompt.setText(wrongMessage);
            return;
        }

        User user = new User(name, password, User.Type.CO);
        userRepo.insert(user);
        close();
    }

    public void registerMerUser() {
        String name = usernameInput.getText();
        String password = passwordInput.getText();

        UserRepo userRepo = new UserRepo();
        if(userRepo.contain(name)) {
            wrongInputPrompt.setText(wrongMessage);
            return;
        }

        User user = new User(name, password, User.Type.ME);
        userRepo.insert(user);
        close();
    }

    public void close() {
        stage.close();
    }
}
