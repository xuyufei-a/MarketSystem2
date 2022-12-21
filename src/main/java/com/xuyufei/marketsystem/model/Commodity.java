package com.xuyufei.marketsystem.model;

import javafx.scene.image.Image;

public class Commodity {
    private final int id;
    private String name;
    private Image image;
    private String imagePath;
    private String text;
    private int price;
    private int owner;
    private Discount discount;

    private boolean status;

    public Commodity(int id, String name, Image image, String text, int price, int owner) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.text = text;
        this.price = price;
        this.owner = owner;
    }

    public Commodity(int id, String name, String imagePath, String text, int price, int owner) {
        this.id = id;
        this.name = name;
        this.image = new Image(imagePath);
        this.imagePath = imagePath;
        this.text = text;
        this.price = price;
        this.owner = owner;
    }

    public Commodity(int id, String name, int price, int owner) {
        this(id, name, (Image) null, null, price, owner);
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isStatus() {
        return status;
    }

    private void setStatus(boolean status) {
        this.status = status;
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
        return status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPrice() {
        if(discount == null) return price;
        return discount.discount(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

}
