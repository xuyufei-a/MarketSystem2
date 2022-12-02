package com.xuyufei.marketsystem.entity;

@FunctionalInterface
public interface Discount {
    public int discount(int price);
}
