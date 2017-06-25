package com.zhengjy.spring;

/**
 * Created by zhengjy on 2017/6/14.
 */
public class User2 {
    private String userName;
    private String email;

    public User2(){

    }
    public User2(String userName) {
        this.userName = userName;
    }

    public User2(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
