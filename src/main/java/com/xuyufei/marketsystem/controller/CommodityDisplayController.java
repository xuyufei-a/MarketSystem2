package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import com.xuyufei.marketsystem.model.Commodity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CommodityDisplayController {
    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label textLabel;

    private Main main;
    private Stage stage;
    private Commodity commodity;

    public void init(Main main, Stage stage, Commodity commodity) {
        this.main = main;
        this.stage = stage;
        this.commodity = commodity;
    }

    public void initialize() {
        String name = commodity.getName();
        String price = "" + commodity.getPrice();
        String owner = commodity.getOwner();
        String text = commodity.getText();

        nameLabel.setText(name);
        priceLabel.setText(price);
        ownerLabel.setText(owner);
        textLabel.setText(text);
    }

    public void close() {
        stage.close();
    }
}
