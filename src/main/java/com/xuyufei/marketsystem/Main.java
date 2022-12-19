package com.xuyufei.marketsystem;

import com.xuyufei.marketsystem.controller.LoginWindowController;
import com.xuyufei.marketsystem.entity.User;
import com.xuyufei.marketsystem.repo.UserRepo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private Stage primaryStage;
    private User user;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        openLoginWindow();

//        if(user.getType() == User.Type.SU.ordinal())    openSuperUserWindow();

//        FXMLLoader loader = new FXMLLoader(
//                Main.class.getResource("loginWindow.fxml"));
//        AnchorPane root = (AnchorPane) loader.load();
//
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("OK");
//        primaryStage.setScene(scene);
//        primaryStage.show();

    }

    public void openLoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("loginWindow.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            LoginWindowController controller = loader.getController();
            controller.setMain(this, primaryStage);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("登录");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        try(UserRepo repo = new UserRepo();) {
            if(repo.checkPassword(username, password)) {
                user = repo.getUserEntityByUsername(username);
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openSuperUserWindow() {
        System.out.println(user.toString());
        System.out.println(user.getUsername());
    }

    public static void main(String[] args) {
        launch();
    }
}
