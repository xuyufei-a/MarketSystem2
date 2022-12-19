package com.xuyufei.marketsystem.entity;

public class CommodityIDAndNum {
    private int commodityID;
    private int num;

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

    public CommodityIDAndNum(int comID, int num) {
        this.commodityID = comID;
        this.num = num;
    }
}
