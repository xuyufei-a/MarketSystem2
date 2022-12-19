package com.xuyufei.marketsystem;

import com.xuyufei.marketsystem.entity.User;
import com.xuyufei.marketsystem.repo.UserRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test {
    public static void main(String[] args) throws IOException {
//        try(UserRepo userRepo = new UserRepo();) {
//            var flag = userRepo.checkPassword("super", "123456");
//            System.out.println(flag);
//        }
//        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:D:/Documents/java_project/MarketSystem/dbs/repo.db");
//            Statement statement = connection.createStatement();) {
//            String sql = "select * from users where username='super'";
//            var rs = statement.executeQuery(sql);
//            while(rs.next()) {
//                System.out.println(rs.getString("username"));
//                System.out.println(rs.getString("password"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
