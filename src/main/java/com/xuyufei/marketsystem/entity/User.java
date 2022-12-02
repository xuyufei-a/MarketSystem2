package com.xuyufei.marketsystem.entity;

public class User {
    public enum Type {
        SU, CO, ME
    }
    private final String username;
    private String password;
    private final Type type;

    private static Type tran(int type) {
        Type ret = switch (type) {
            case 0 -> Type.SU;
            case 1 -> Type.CO;
            case 2 -> Type.ME;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        return ret;
    }

    public User(String username, String password, Type type) {
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int type) {
        this(username, password, tran(type));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type.ordinal();
    }
}
