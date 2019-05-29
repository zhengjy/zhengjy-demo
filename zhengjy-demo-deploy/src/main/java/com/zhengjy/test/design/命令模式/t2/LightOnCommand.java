package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/26 15:03.
 * 实现一个打开电灯的命令
 *      假设想实现一个打开电灯的命令，根据厂商提供的类
 */
public class LightOnCommand implements Command {
    private Light light;

    /**
     * 构造器传入某个电灯(比方说:客厅的电灯)，以便让这个命令控制，然后记录在实例变量，一旦调用execute()
     * ，就由这个电灯对象称为接受者，负责接受请求。
     * @param light
     */
    public LightOnCommand(Light light){
        this.light = light;
    }

    /**
     * 调用接收对象(我们正在控制的电灯)的on()方法
     */
    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
