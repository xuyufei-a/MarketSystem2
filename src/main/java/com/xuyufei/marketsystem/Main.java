package com.xuyufei.marketsystem;

import com.xuyufei.marketsystem.controller.*;
import com.xuyufei.marketsystem.model.Commodity;
import com.xuyufei.marketsystem.model.User;
import com.xuyufei.marketsystem.repo.UserRepo;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

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

    public int login(String username, String password) {
        try(UserRepo repo = new UserRepo();) {
            int flag = repo.checkPassword(username, password);
            user = repo.getUserEntityByUsername(username);
            return flag;
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

    public void openSuperBanWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("superUserMain-ban.fxml"));
            SplitPane root = (SplitPane) loader.load();
            SuperUserMainBanController controller = loader.getController();
            controller.init(primaryStage, this, user);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Market System--super user--ban mode");
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

    public void openMerGoodsWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("merUserMain-myGoods.fxml"));
            SplitPane root = (SplitPane) loader.load();
            MerUserMainGoodsController controller = loader.getController();
            controller.init(primaryStage, this, user);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Market System--merchant user--goods mode");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openCommonUserCommoditiesWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("commonUserCommodities.fxml"));
            SplitPane root = (SplitPane) loader.load();
            CommonUserCommoditiesController controller = loader.getController();
            controller.init(primaryStage, this, user);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Market System--common user--commodities");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openCommodityModifyWindow(Commodity commodity) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("commodityModifyWindow.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            CommodityModifyController controller = loader.getController();

            Stage secondStage = new Stage();
            secondStage.initModality(Modality.APPLICATION_MODAL);
            controller.init(this, secondStage, commodity);

            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.setTitle("Modify");
            secondStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openCommodityAddWindow(ObservableList<Commodity> goodslist, String owner) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("commodityAddWindow.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            CommodityAddController controller = loader.getController();

            Stage secondStage = new Stage();
            secondStage.initModality(Modality.APPLICATION_MODAL);
            controller.init(this, secondStage, goodslist, owner);

            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.setTitle("Add");
            secondStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openCommodityDisplayWindow(Commodity commodity) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("commodityDisplayWindow.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            CommodityDisplayController controller = loader.getController();

            Stage secondStage = new Stage();
            secondStage.initModality(Modality.APPLICATION_MODAL);
            controller.init(this, secondStage, commodity);

            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.setTitle("Display");
            secondStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void openRegisterWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    Main.class.getResource("registerWindow.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            RegisterWindowController controller = loader.getController();

            Stage secondStage = new Stage();
            secondStage.initModality(Modality.APPLICATION_MODAL);
            controller.setMain(this, secondStage);

            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.setTitle("register");
            secondStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");
    public static boolean isNumeric(String str) {
        return str != null && NUMBER_PATTERN.matcher(str).matches();
    }

    public static void main(String[] args) {
        launch();
    }
}
