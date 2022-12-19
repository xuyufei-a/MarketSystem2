package com.xuyufei.marketsystem;

import com.xuyufei.marketsystem.controller.CommonUserMainController;
import com.xuyufei.marketsystem.controller.LoginWindowController;
import com.xuyufei.marketsystem.controller.MerUserMainController;
import com.xuyufei.marketsystem.controller.SuperUserMainController;
import com.xuyufei.marketsystem.entity.User;
import com.xuyufei.marketsystem.repo.UserRepo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
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
    }

    public void openMainWindow() {
        if(user.getType() == User.Type.SU.ordinal()) {
            openSuperUserWindow();
        } else if(user.getType() == User.Type.CO.ordinal()) {
            openCommonUserWindow();
        } else {
            openMerUserWindow();
        }

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
        System.out.println(user.getUsername());
        System.out.println("open super user's window");

        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("superUserMain.fxml"));
            SplitPane root = (SplitPane) loader.load();
            SuperUserMainController controller = loader.getController();
            controller.init(primaryStage, this, user);


            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Market System--super user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openCommonUserWindow() {
        System.out.println(user.getUsername());
        System.out.println("open common user's window");

        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("commonUserMain.fxml"));
            SplitPane root = (SplitPane) loader.load();
            CommonUserMainController controller = loader.getController();
            controller.init(primaryStage, this, user);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Market System--common user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openMerUserWindow() {
        System.out.println(user.getUsername());
        System.out.println("open merchant user's window");

        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("merUserMain.fxml"));
            SplitPane root = (SplitPane) loader.load();
            MerUserMainController controller = loader.getController();
            controller.init(primaryStage, this, user);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Market System--merchant user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
