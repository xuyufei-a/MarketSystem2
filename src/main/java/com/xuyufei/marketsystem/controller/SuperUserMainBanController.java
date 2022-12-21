package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import com.xuyufei.marketsystem.model.User;
import com.xuyufei.marketsystem.repo.UserRepo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

public class SuperUserMainBanController {
    private User user;
    private Stage stage;
    private Main main;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableColumn<User, String> usernameText;
    @FXML
    private TableColumn<User, String> passwordText;
    @FXML
    private TableColumn<User, Integer> status;

    private ObservableList<User> userList;

    public void initialize() {
        usernameText.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        passwordText.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
        status.setCellValueFactory(new PropertyValueFactory<User, Integer>("status"));
    }

    public void setTableData() {
        UserRepo userRepo = new UserRepo();
        userList = FXCollections.observableList(userRepo.getAllUsers());
    }
    public void init(Stage stage, Main main, User user) {
        this.stage = stage;
        this.main = main;
        this.user = user;

        setTableData();
        tableView.setItems(userList);
        tableView.getSelectionModel().select(0);
    }

    @FXML
    public void exit() {

        stage.close();
    }
}
