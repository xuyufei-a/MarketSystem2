package com.xuyufei.marketsystem;

import java.util.ArrayList;
import java.util.Collection;

public class MerchantUser extends User implements Login {
    private Collection<Integer> commodities;

    public MerchantUser(String username, String password) {
        super(username, password);
        commodities = new ArrayList<>();
    }

    // TODO: 2022/11/27
    @Override
    public boolean login(String username, String password) {
        return false;
    }

    public boolean setDiscount(int orderID, Discount f) {
        //找到ID对应的
        return false;
    }
}
