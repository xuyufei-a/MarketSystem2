package com.xuyufei.marketsystem.repo;

import com.xuyufei.marketsystem.entity.CommonUser;
import com.xuyufei.marketsystem.entity.MerchantUser;
import com.xuyufei.marketsystem.entity.SuperUser;
import com.xuyufei.marketsystem.entity.User;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepo extends Repo implements Closeable {

    private static Connection connection;
    private UserRepo() {
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

    public <T extends User> boolean insert(T user) {
        var username = user.getUsername();
        var password = user.getPassword();
        var type = user.getType();

        final String sql = "insert into users(username, password, type) " +
                "values(" + username + "," + password + "," + type + ")";

        try(Statement statement = connection.createStatement();) {
            int rowAffected = statement.executeUpdate(sql);
            if(rowAffected > 0) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserEntityByUsername(String username) {
        final String sql =  "select * from users where username=" + username;
        try(Statement statement = connection.createStatement();) {
            var resultSet = statement.executeQuery(sql);
            if(resultSet.next() == false)   return null;

            var name = resultSet.getString("username");
            var password = resultSet.getString("password");
            var type =resultSet.getInt("type");

            User ret;
            if(type == User.Type.CO.ordinal())  ret = new CommonUser(username, password);
            else if(type == User.Type.ME.ordinal()) ret = new MerchantUser(username, password);
            else                                ret = SuperUser.getSuperUser();
            return ret;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIDByUsername(String username) {
        final String sql = "select * from users where username=" + username;
        try(Statement statement = connection.createStatement();) {
            var resultSet = statement.executeQuery(sql);
            if(resultSet.next() == false)   return -1;

            var id = resultSet.getInt("id");

            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends User> int getIDByUser(T user) {
        return getIDByUsername(user.getUsername());
    }

    public boolean checkPassword(String username, String password) {
        final String sql = "select * from users where username=" + username;
        try(Statement statement = connection.createStatement();) {
            var resultSet = statement.executeQuery(sql);
            if(resultSet.next() == false)   return false;

            var pass = resultSet.getString("password");

            return pass.equals(password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
