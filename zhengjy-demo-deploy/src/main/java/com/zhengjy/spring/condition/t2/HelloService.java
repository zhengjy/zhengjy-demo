package com.zhengjy.spring.condition.t2;

/**
 * Created by zhengjy on 2017/7/16.
 */
public class HelloService {

    private String msg;

    public String sayHello(){
        return "Hello" + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
