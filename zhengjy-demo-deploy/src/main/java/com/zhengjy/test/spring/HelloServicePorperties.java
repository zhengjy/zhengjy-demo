package com.zhengjy.test.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhengjy on 2017/5/3.
 */
@ConfigurationProperties(prefix = "hello")
public class HelloServicePorperties {
    private static final String MSG = "world";

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}