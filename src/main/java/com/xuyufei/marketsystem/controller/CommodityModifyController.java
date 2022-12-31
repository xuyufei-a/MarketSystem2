package com.xuyufei.marketsystem.controller;

import com.xuyufei.marketsystem.Main;
import com.xuyufei.marketsystem.model.Commodity;
import com.xuyufei.marketsystem.repo.CommodityRepo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CommodityModifyController {
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
    private Commodity commodity;

    public void init(Main main, Stage stage, Commodity commodity) {
        this.main = main;
        this.stage = stage;
        this.commodity = commodity;

        String name = commodity.getName();
        String price = "" + commodity.getPrice();
        String text = commodity.getText();

        nameField.setText(name);
        priceField.setText(price);
        textField.setText(text);
    }

    public void close() {
        stage.close();
    }

    public void modify() {
        String name = nameField.getText();
        String p = priceField.getText();
        if(!main.isNumeric(p)) {
            wrongPromptLabel.setText(wrongPrompt1);
            return;
        }
        CommodityRepo commodityRepo = new CommodityRepo();
        int id = commodityRepo.getIDByName(commodity.getName());

        commodity.setName(nameField.getText());
        commodity.setPrice(Integer.valueOf(p));
        commodity.setText(textField.getText());
        commodityRepo.modifyByID(commodity, id);

        close();
    }
}
