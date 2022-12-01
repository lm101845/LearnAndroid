package com.dongnaoedu.chapter06.enity;

public class LoginInfo {

    public int id;
    public String phone;
    public String password;
    public boolean remember = false;

    public LoginInfo(){}


    public LoginInfo(String phone, String password, boolean remember) {
        this.phone = phone;
        this.password = password;
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", remember=" + remember +
                '}';
    }
}
