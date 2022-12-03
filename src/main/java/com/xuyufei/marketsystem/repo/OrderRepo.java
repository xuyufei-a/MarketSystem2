package com.xuyufei.marketsystem.repo;

import com.xuyufei.marketsystem.entity.Order;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderRepo extends Repo implements Closeable {
    private static Connection connection;

    OrderRepo() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertEntity(Order order) {
        try(Statement statement = connection.createStatement();) {
            String sql = "insert into orders(commodityID, num, payment, buyerID) " +
                    "values(" + order.getCommodityID() + "," + order.getNum() + ","
                    + order.getPayment() + "," + order.getBuyerID() + ")";
            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected > 0) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getEntityByID(int id) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from orders where id=" + id;
            var resultSet = statement.executeQuery(sql);
            if(resultSet.next() == false)   return null;

            var commodityID = resultSet.getInt("commodityID");
            var num = resultSet.getInt("num");
            var payment = resultSet.getDouble("payment");
            var buyerID = resultSet.getInt("buyerID");
            return new Order(id, commodityID, num, payment, buyerID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setStatusByID(int id, boolean status) {
        try(Statement statement = connection.createStatement();) {
            String sql = "update orders set status =" + status +
                    " where id =" + id;
            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected > 0) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Iterable<Integer> getIDsByFieldID(int ID, String field) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from orders where " + field + "=" + ID;
            var resultSet = statement.executeQuery(sql);

            var ret = new ArrayList<Integer>();
            while(resultSet.next()) {
                ret.add(resultSet.getInt("id"));
            }
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Integer> getIDsByBuyerID(int buyerID) {
        return getIDsByFieldID(buyerID, "buyerID");
    }

    public Iterable<Integer> getIDsByCommodity(int comID) {
        return getIDsByFieldID(comID, "commodityID");
    }
}
