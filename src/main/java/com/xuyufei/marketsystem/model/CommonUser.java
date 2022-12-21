package com.xuyufei.marketsystem.model;

import java.util.ArrayList;
import java.util.Collection;

public class CommonUser extends User implements Login {

    Collection<Integer> list;
    Collection<Integer> orders;

    public CommonUser(String username, String password) {
        super(username, password, Type.CO);

        list = new ArrayList<>();
        orders = new ArrayList<>();
    }

    // TODO: 2022/11/27
    public void comment(String comments) {

    }

    public boolean pay(Collection<Integer> List) {
        //计算价格
        for(int id: List) {

        }

        //支付，验证支付是否成功

        return true;
    }

    public boolean refund(int orderID) {
        //根据ID找订单

        //修改订单状态，退款

        return true;
    }

    public Iterable<Integer> getList() {
        return list;
    }


    // TODO: 2022/11/27
    @Override
    public boolean login(String username, String password) {

        return false;
    }
}
