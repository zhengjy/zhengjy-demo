package com.zhengjy.test.design.命令模式.t2;

import org.apache.velocity.runtime.directive.Macro;

/**
 * Created by zhengjy on 2019/5/27.
 */
public class MacroMain {
    public static void main(String[] args) {
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

        Command[] onCommands = {lightOn,stereoOn};
        Command[] offCommands = {lightOff,stereoOff};

        MacroCommand macroOn = new MacroCommand(onCommands);
        MacroCommand macroOff = new MacroCommand(offCommands);

        remote.setCommand(0,macroOn,macroOff);
        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);
        remote.undoButtonWasPushed();
    }
}
