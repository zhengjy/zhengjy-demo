package com.zhengjy.test.design.命令模式.t2;

import lombok.ToString;

import java.util.Arrays;

/**
 * Created by zhengjy on 2019/5/27.
 * 实现遥控器
 */
@ToString
public class RemoteControl {
    /**
     * 遥控器处理7个开与关的命令，使用相应数组记录这些命令
     */
    private Command[] onCommands;
    private Command[] offCommands;

    /**
     * 前一个命令被记录在这里
     * 用来追踪最后被调用的命令，然后，不管何时撤回按钮被按下，都可以取出这个命令
     * 并调用对应的undo()方法
     */
    Command undoCommand;

    public RemoteControl(){
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command noCommand = new NoCommand();
        for (int i=0;i<7;i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    /**
     * 这些命令将记录在开关数组中对应的插槽位置，
     * @param solt 位置
     * @param onCommand 开的命令
     * @param offCommand 关的命令
     */
    public void setCommand(int solt,Command onCommand,Command offCommand){
        onCommands[solt] = onCommand;
        offCommands[solt] = offCommand;
    }

    /**
     * 按下按钮时触发
     * @param solt
     */
    public void onButtonWasPushed(int solt){
        onCommands[solt].execute();
        undoCommand = onCommands[solt];
    }
    public void offButtonWasPushed(int solt){
        offCommands[solt].execute();
        undoCommand = offCommands[solt];
    }

    public void undoButtonWasPushed(){
        undoCommand.undo();
    }


}
