package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by zhengjy on 2019/5/27.
 */
public class CeilingFanMan {
    public static void main(String[] args) {
        RemoteControl remote =  new RemoteControl();

        CeilingFan ceilingFan = new CeilingFan("厂商1");

        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
        CeilingFanLowCommand ceilingFanLow = new CeilingFanLowCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

        remote.setCommand(1,ceilingFanHigh,ceilingFanOff);
        remote.setCommand(2,ceilingFanLow,ceilingFanOff);
        //按下执行高速
        remote.onButtonWasPushed(1);
        //关闭
        remote.offButtonWasPushed(1);
        //还原高速
        remote.undoButtonWasPushed();

        System.out.println(remote);

        //执行低速
        remote.onButtonWasPushed(2);
        //关闭低速
        remote.offButtonWasPushed(2);
        //撤销低速
        remote.undoButtonWasPushed();

        //执行高速
        remote.onButtonWasPushed(1);
        //还原低速
        remote.undoButtonWasPushed();
        //执行高速
        remote.onButtonWasPushed(1);
        //还原低速
        remote.undoButtonWasPushed();

    }
}
