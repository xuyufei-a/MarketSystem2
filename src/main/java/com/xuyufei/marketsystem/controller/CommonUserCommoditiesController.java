package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import com.xuyufei.marketsystem.model.Commodity;
import com.xuyufei.marketsystem.model.User;
import com.xuyufei.marketsystem.repo.CommodityRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CommonUserCommoditiesController {
    @FXML
    private TableView<Commodity> tableView;
    @FXML
    private TableColumn<Commodity, String> nameColumn;
    @FXML
    private TableColumn<Commodity, Integer> priceColumn;
    @FXML
    private TableColumn<Commodity, String> ownerColumn;

    private ObservableList<Commodity> goodsList;

    private User user;
    private Stage stage;
    private Main main;

    public void setTableData() {
        CommodityRepo commodityRepo = new CommodityRepo();
        goodsList = FXCollections.observableList(commodityRepo.getAllAvailableCommodities());
    }

    public void init(Stage stage, Main main, User user) {
        this.stage = stage;
        this.main = main;
        this.user = user;

        setTableData();
        tableView.setItems(goodsList);
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Commodity, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Commodity, Integer>("price"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<Commodity, String>("owner"));
    }

    @FXML
    public void exit() {
        stage.close();
    }

    @FXML
    public void check() {
        int index = tableView.getSelectionModel().getSelectedIndex();
        Commodity commodity = goodsList.get(index);
        main.openCommodityDisplayWindow(commodity);
    }
}
