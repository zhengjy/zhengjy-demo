package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/26 15:02.
 */
public class Light {
    private String name;
    public Light(String name){
        this.name = name;
    }

    public void  on(){
        System.out.println(name +"light on");

    }

    public void off(){
        System.out.println(name +"light off");
    }
}
