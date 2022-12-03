package com.xuyufei.marketsystem.repo;

public class Repo {
    // TODO: 2022/12/4
    // maybe it's better to implements Closeable in the Repo class
    // and to fullfill it, the connection variant should also be declared here
    // and it should not be static variant
    protected static final String URL = "jdbc:sqlite:D:/Documents/java_project/MarketSystem/dbs/repo.db";
}
