package com.xuyufei.marketsystem.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class User {
    public enum Type {
        SU, CO, ME
    }
    private final String username;
    private String password;
    private final Type type;
    private IntegerProperty status;

    public final IntegerProperty statusProperty() {
        if(status == null)  {
            status = new SimpleIntegerProperty();
        }
        return status;
    }

    private static Type tran(int type) {
        Type ret = switch (type) {
            case 0 -> Type.SU;
            case 1 -> Type.CO;
            case 2 -> Type.ME;
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        return ret;
    }

    public User(String username, String password, Type type, int status) {
        this.type = type;
        this.username = username;
        this.password = password;
        setStatus(status);
    }

    public User(String username, String password, int type) {
        this(username, password, type, 1);
    }

    public User(String username, String password, Type type) {
        this(username, password, type, 1);
    }

    public User(String username, String password, int type, int status) {
        this(username, password, tran(type), status);
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

    public int getStatus() {
        return statusProperty().get();
    }

    public void setStatus(int status) {
        statusProperty().set(status);
    }
}
