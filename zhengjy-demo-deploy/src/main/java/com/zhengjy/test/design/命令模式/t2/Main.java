package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/26 15:21.
 */
public class Main {
    public static void main(String[] args) {
//        //遥控器就是调用者，会传入一个命令对象，可以用来发出请求。
//        SimpleRemoteControl remote = new SimpleRemoteControl();
//        //创建一个电灯对象，此对象也就是请求的接受者
//        Light light = new Light();
//        //创建一个命令，然后将接收者传给他
//        LightOnCommand lightOn = new LightOnCommand(light);
//        remote.setSlot(lightOn);
//        //按下按钮
//        remote.buttonWasPressed();

        RemoteControl remote =  new RemoteControl();
        //创建一个电灯对象，此对象也就是请求的接受者
        Light light = new Light("厂商1");
        //创建一个音响对象
        Stereo stereo = new Stereo("厂商1");

        LightOnCommand lightOn = new LightOnCommand(light);
        LightOffCommand lightOff = new LightOffCommand(light);

        StereoOnWithCDCommand stereoOn = new StereoOnWithCDCommand(stereo);
        StereoOffWithCDCommand stereoOff = new StereoOffWithCDCommand(stereo);

        remote.setCommand(0,lightOn,lightOff);

        remote.setCommand(1,stereoOn,stereoOff);

        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);
        //打开灯->关闭灯->执行撤回（还原打开灯）
        remote.undoButtonWasPushed();
        System.out.println(remote);
        remote.onButtonWasPushed(1);
        remote.offButtonWasPushed(1);
        //打开音响->关闭音响->执行撤回（还原打开音响）
        remote.undoButtonWasPushed();

        //命令执行结果。每个装置的输出都是由厂商类提供。Light和Stereo可以由不同厂商创建

    }
}
