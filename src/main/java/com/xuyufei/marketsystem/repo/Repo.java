package com.xuyufei.marketsystem.repo;

import java.io.IOException;
import java.nio.file.Path;

public class Repo {
    protected static String URL;
    Repo() {
        try {
            if (URL == null) {
                URL = "jdbc:sqlite:" + Path.of(".").toRealPath() + "/repo.db";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
