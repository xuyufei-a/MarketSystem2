package com.xuyufei.marketsystem.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

public class Commodity {
    private int id;
    private StringProperty name;
    private Image image;
    private String imagePath;
    private String text;
    private IntegerProperty price;
    private String owner;
    private Discount discount;

    private BooleanProperty status;

    public final StringProperty nameProperty() {
        if(name == null)    {
            name = new SimpleStringProperty();
        }
        return name;
    }

    public final IntegerProperty priceProperty() {
        if(price == null) {
            price = new SimpleIntegerProperty();
        }
        return price;
    }

    public final BooleanProperty statusProperty() {
        if(status == null) {
            status = new SimpleBooleanProperty();
        }
        return status;
    }

    public Commodity(int id, String name, String text, int price, String owner, boolean status) {
        this.id = id;
        setName(name);
        this.text = text;
        setPrice(price);
        this.owner = owner;
        setStatus(status);
    }

    public Commodity(int id, String name, String imagePath, String text, int price, String owner, boolean status) {
        this(id, name, text, price, owner, status);
        this.imagePath = imagePath;
    }

    public Commodity(int id, String name, Image image, String text, int price, String owner, boolean status) {
        this(id, name, text, price, owner, status);
        this.image = image;
    }

    public Commodity(int id, String name, int price, String owner, boolean status) {
        this(id, name, (Image) null, null, price, owner, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean getStatus() {
        return statusProperty().get();
    }

    public void setStatus(boolean status) {
        statusProperty().set(status);
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Image getImage() {
        return image;
    }

    public boolean isAvailable() {
        return getStatus();
    }

    public String getName() {
        return nameProperty().get();
    }

    public void setName(String name) {
        nameProperty().set(name);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPrice() {
        int p = priceProperty().get();
        if(discount == null) return p;
        return discount.discount(p);
    }

    public void setPrice(int price) {
        priceProperty().set(price);
    }

    public String getOwner() {
        return owner;
    }

public void setOwner(String owner) {
        this.owner = owner;
    }

}
