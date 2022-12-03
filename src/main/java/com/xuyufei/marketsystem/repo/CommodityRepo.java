package com.xuyufei.marketsystem.repo;

import com.xuyufei.marketsystem.entity.Commodity;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommodityRepo extends Repo implements Closeable {
    private static Connection connection;

    public CommodityRepo() {
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

    public boolean insertEntity(Commodity com) {
        try(Statement statement = connection.createStatement();) {
            String sql = "insert into commodity(name, imagePath, text, price, owner)" +
                    " values(" + com.getName() + "," + com.getImagePath() + "," +
                    com.getText() + "," + com.getPrice() + "," + com.getOwner() + ")";
            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected > 0) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Integer> getIDsByName(String name) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity where name=" + name;
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

    public Iterable<Integer> getIDsByEntityName(Commodity com) {
        return getIDsByName(com.getName());
    }

    // TODO: 2022/12/4
    // In the function, the codes to import a image may be incorrect
    public Commodity getEntityByID(int id) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity where id=" + id;
            var resultSet = statement.executeQuery(sql);
            if(resultSet.next() == false)   return null;

            var id2 = resultSet.getInt("id");
            var name = resultSet.getString("name");
            var imagePath = resultSet.getString("imagePath");
            var text = resultSet.getString("text");
            var price = resultSet.getInt("price");
            var owner = resultSet.getInt("owner");
            return new Commodity(id2, name, imagePath, text, price, owner);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Iterable<Integer> getIDsByOwnerID(int ownerID) {
        try(Statement statement = connection.createStatement();) {
            String sql = "select * from commodity where owner=" + ownerID;
            var resultSet = statement.executeQuery(sql);

            var ret = new ArrayList<Integer>();
            while(resultSet.next()) {
                ret.add(resultSet.getInt("owner"));
            }
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
