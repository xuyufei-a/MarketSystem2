package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import com.xuyufei.marketsystem.model.Commodity;
import com.xuyufei.marketsystem.model.User;
import com.xuyufei.marketsystem.repo.CommodityRepo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

public class MerUserMainGoodsController {
    private User user;
    private Stage stage;
    private Main main;

    @FXML
    private TableView<Commodity> tableView;
    @FXML
    private TableColumn<Commodity, String> nameColumn;
    @FXML
    private TableColumn<Commodity, Integer> priceColumn;
    @FXML
    private TableColumn<Commodity, Boolean> statusColumn;

    private ObservableList<Commodity> goodsList;

    private void setTableData() {
        CommodityRepo commodityRepo = new CommodityRepo();
        goodsList = FXCollections.observableList(commodityRepo.getAllCommoditiesOwnedByUser(user.getUsername()));
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Commodity, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Commodity, Integer>("price"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Commodity, Boolean>("status"));
    }

    public void init(Stage stage, Main main, User user) {
        this.stage = stage;
        this.main = main;
        this.user = user;

        setTableData();
        tableView.setItems(goodsList);
        tableView.getSelectionModel().select(0);
    }

    @FXML
    public void modify() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        Commodity commodity = goodsList.get(index);
        main.openCommodityModifyWindow(commodity);
    }

    @FXML
    public void add() {
        main.openCommodityAddWindow(goodsList, user.getUsername());
    }

    @FXML
    public void del() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        String name = goodsList.get(index).getName();
        CommodityRepo commodityRepo = new CommodityRepo();
        commodityRepo.delByName(name);
        goodsList.remove(index);
    }

    @FXML
    public void on() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        Commodity good = goodsList.get(index);
        good.setStatus(true);

        CommodityRepo commodityRepo = new CommodityRepo();
        commodityRepo.setStatusByName(good.getName(), true);
    }

    @FXML
    public void off() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        Commodity good = goodsList.get(index);
        good.setStatus(false);

        CommodityRepo commodityRepo = new CommodityRepo();
        commodityRepo.setStatusByName(good.getName(), false);
    }

    @FXML
    public void exit() {
        stage.close();
    }
}
