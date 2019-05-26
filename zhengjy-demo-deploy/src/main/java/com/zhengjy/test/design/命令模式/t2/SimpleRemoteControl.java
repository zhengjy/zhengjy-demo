package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/26 15:17.
 * 使用命令对象，假设我们有一个遥控器，它只有一个按钮和对应的插槽，可以控制装置
 */
public class SimpleRemoteControl {
    Command slot;


    /**
     * 这个方法用来设置插槽控制的命令。如果这段代码的客户想要改变遥控器按钮的行为，可以多次调用这个方法。
     * @param slot
     */
    public void setSlot(Command slot) {
        this.slot = slot;
    }

    /**
     * 当按下按钮时，这个方法就会被调用，使得当前命令衔接插槽，并调用它的execute()方法。
     */
    public void buttonWasPressed(){
        slot.execute();
    }
}
