package com.xuyufei.marketsystem.model;

@FunctionalInterface
public interface Discount {
    public int discount(int price);
}
