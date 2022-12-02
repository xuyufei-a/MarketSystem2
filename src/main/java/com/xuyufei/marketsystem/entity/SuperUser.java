package com.xuyufei.marketsystem.entity;

public class SuperUser extends User implements Login {
    private static SuperUser superUser;

    private SuperUser(String username, String password) {
        super(username, password, Type.SU);
    }

    public static SuperUser getSuperUser() {
        if(superUser == null)   superUser = new SuperUser("super", "123456");
        return superUser;
    }

    @Override
    public void setPassword(String password) {
        throw new UnsupportedOperationException();
    }

    // TODO: 2022/11/27
    @Override
    public boolean login(String username, String password) {
        return false;
    }
}
