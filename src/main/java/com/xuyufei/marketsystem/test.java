package com.xuyufei.marketsystem;

import com.xuyufei.marketsystem.entity.User;
import com.xuyufei.marketsystem.repo.UserRepo;

import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        try(UserRepo userRepo = new UserRepo();) {
            var flag = userRepo.checkPassword("super", "123456");
            System.out.println(flag);
        }
    }
}
