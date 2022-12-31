package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import com.xuyufei.marketsystem.model.Commodity;
import com.xuyufei.marketsystem.repo.CommodityRepo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CommodityAddController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField textField;
    @FXML
    private Label wrongPromptLabel;
    private final String wrongPrompt1 = "价格必须为整数";
    private final String wrongPrompt2 = "该商品名已存在";

    private Main main;
    private Stage stage;
    private ObservableList<Commodity> goodslist;
    private String owner;

    public void init(Main main, Stage stage, ObservableList<Commodity> goodslist, String owner) {
        this.main = main;
        this.stage = stage;
        this.goodslist = goodslist;
        this.owner = owner;
    }

    public void add() {
        CommodityRepo commodityRepo = new CommodityRepo();
        String name = nameField.getText();
        if(commodityRepo.contains(name)) {
            wrongPromptLabel.setText(wrongPrompt2);
            return;
        }

        String p = priceField.getText();
        int price = 0;
        if(!main.isNumeric(p)) {
            wrongPromptLabel.setText(wrongPrompt1);
            return;
        }
        price = Integer.valueOf(p);
        String text = textField.getText();

        Commodity commodity = new Commodity(-1, name, text, price, owner, true);

        commodityRepo.insertEntity(commodity);
        goodslist.add(commodity);

        close();
    }

    public void close() {
        stage.close();
    }
}
