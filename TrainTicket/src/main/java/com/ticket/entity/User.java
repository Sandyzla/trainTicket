package com.ticket.entity;

public class User {
    private String idNum;
    private String phone;
    private String userName;
    private String password;
    private int money;
    private int version;

    public User(String idNum, String phone, String userName, String password, int money, int version) {
        this.idNum = idNum;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.money = money;
        this.version = version;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
