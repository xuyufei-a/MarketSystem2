package com.xuyufei.marketsystem.entity;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private int commodityID;
    private int num;
    private double payment;
    private int buyerID;

    public Order(int orderID, int commodityID, int num, double payment, int buyerID) {
        this.orderID = orderID;
        this.commodityID = commodityID;
        this.num = num;
        this.payment = payment;
        this.buyerID = buyerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }
}
