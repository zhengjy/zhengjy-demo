package com.zhengjy.test.design.命令模式.t2;

/**
 * 吊扇：吊扇允许有多种转速，也允许被关闭
 * Created by zhengjy on 2019/5/27.
 */
public class CeilingFan {

    public static final int HIGH = 3;
    public static final int MEDIUM = 2;
    public static final int LOW = 1;
    public static final int OFF = 0;

    String location;
    /**
     * 吊扇的速度
     */
    int speed;

    public CeilingFan(String location){
        this.location = location;
        speed = OFF;
    }

    public void high(){
        System.out.println(location+" ceilingFan high");
        speed = HIGH;
    }

    public void medium(){
        speed = MEDIUM;
    }
    public void low(){
        System.out.println(location+" ceilingFan low");
        speed = LOW;
    }
    public void off(){
        speed = OFF;
        System.out.println(location+" ceilingFan off");
    }
    public int getSpeed(){
        return speed;
    }
}
