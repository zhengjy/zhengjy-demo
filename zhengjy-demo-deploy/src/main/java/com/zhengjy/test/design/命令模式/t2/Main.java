package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/26 15:21.
 */
public class Main {
    public static void main(String[] args) {
        //遥控器就是调用者，会传入一个命令对象，可以用来发出请求。
        SimpleRemoteControl remote = new SimpleRemoteControl();
        //创建一个电灯对象，此对象也就是请求的接受者
        Light light = new Light();
        //创建一个命令，然后将接收者传给他
        LightOnCommand lightOn = new LightOnCommand(light);
        remote.setSlot(lightOn);
        //按下按钮
        remote.buttonWasPressed();
    }
}
