package com.model;

import java.sql.SQLException;

public class User {
    private String account;
    private String password;

    public User(String as,String pa){
        this.account = as;
        this.password = pa;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isExist() throws SQLException {
        User user  = new User(account,password);
        DBHelper dbHelper =new DBHelper();
        return dbHelper.Query(user);
    }
}
