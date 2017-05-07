package com.zhengjy.test.spring;

/**
 * Created by zhengjy on 2017/5/3.
 */
public class HelloService {
    private String msg;

    public String sayHello(){
        return "Hello " + msg;
    }

    public String getMsg() {
        return msg;
    }
}
