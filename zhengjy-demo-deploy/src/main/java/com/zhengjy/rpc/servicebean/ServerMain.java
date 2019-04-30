package com.zhengjy.rpc.servicebean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Jiyang.Zheng on 2019/1/11 16:50.
 */
public class ServerMain {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring/rpc-invoke-config.xml");

        System.out.println("---rpc server start---");
    }
}
