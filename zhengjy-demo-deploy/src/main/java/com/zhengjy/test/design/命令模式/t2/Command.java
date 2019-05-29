package com.zhengjy.test.design.命令模式.t2;

/**
 * Created by Jiyang.Zheng on 2019/5/26 15:01.
 *
 * 让所有的命令对象实现相同的包含一个方法的接口
 */
public interface Command {

    /**
     * 执行开关等命令
     */
    void  execute();

    /**
     * 撤销，不管execute执行了什么，undo都会倒转过来
     */
    void undo();
}
