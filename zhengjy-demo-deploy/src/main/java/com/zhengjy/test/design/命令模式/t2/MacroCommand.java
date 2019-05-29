package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by zhengjy on 2019/5/27.
 * 宏命令，同时控制电灯、音响、电风扇
 */
public class MacroCommand implements Command {
    Command[] commands;

    public MacroCommand(Command[] commands){
        this.commands = commands;
    }
    @Override
    public void execute() {
        for (int i=0;i<commands.length;i++){
            commands[i].execute();
        }
    }

    @Override
    public void undo() {
        for (int i=0;i<commands.length;i++){
            commands[i].undo();
        }
    }
}
