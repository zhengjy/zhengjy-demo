package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by zhengjy on 2019/5/27.
 */
public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
