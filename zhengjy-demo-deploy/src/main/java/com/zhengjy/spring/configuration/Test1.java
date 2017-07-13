package com.zhengjy.spring.configuration;

/**
 * Created by zhengjy on 2017/7/13.
 */
public class Test1 {

    public Test1(String name) {
        this.name = name;
    }

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
