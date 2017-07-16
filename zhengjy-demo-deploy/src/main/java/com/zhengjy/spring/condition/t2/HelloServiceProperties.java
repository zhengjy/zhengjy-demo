package com.zhengjy.spring.condition.t2;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhengjy on 2017/7/16.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {
    private static final String MSG = " world";

    private String msg = MSG;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
