package com.xuyufei.marketsystem.repo;

import com.xuyufei.marketsystem.entity.CommodityIDAndNum;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ListRepo extends Repo implements Closeable {
    private Connection connection;
    private String tableName;
    private static final String S = "listOfUser";

    public ListRepo(int userID) {
        this.tableName = S + userID;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            DatabaseMetaData meta = connection.getMetaData();
            var resultSet = meta.getTables(null, null, tableName, null);
            if(resultSet.next() == false)   createTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() {
        try (Statement statement = connection.createStatement();) {
            String sql = "create table " + tableName + "(" +
                "commodityID integer not null primary key autoincrement unique, " +
                "num integer not null)";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertByIDAndNum(int comID, int num) {
        try (Statement statement = connection.createStatement();) {
            String sql = "insert into " + tableName + "(commodityID, num) " +
                    "values(" + comID + ", " + num + ")";
            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected > 0) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertByEntity(CommodityIDAndNum c) {
        return insertByIDAndNum(c.getCommodityID(), c.getNum());
    }

    public Iterable<CommodityIDAndNum> getAllItems() {
        try (Statement statement = connection.createStatement();) {
            String sql = "select * from " + tableName;
            var resultSet = statement.executeQuery(sql);

            var ret = new ArrayList<CommodityIDAndNum>();
            while(resultSet.next()) {
                ret.add(new CommodityIDAndNum(resultSet.getInt("commodityID"), resultSet.getInt("num")));
            }
            return ret;
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
}
