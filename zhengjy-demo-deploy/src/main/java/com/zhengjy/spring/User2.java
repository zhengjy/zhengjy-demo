package com.zhengjy.spring;

import com.zhengjy.spring.factory_bean.Car;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhengjy on 2017/6/14.
 */
public class User2 {
    private String userName;
    private String email;

    private Car car2;

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

    public Car getCar2() {
        return car2;
    }

    public void setCar2(Car car233) {
        this.car2 = car233;
    }
}
