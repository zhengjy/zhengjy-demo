package com.zhengjy.test.design.命令模式.t2;

/**
 * 音响
 * Created by zhengjy on 2019/5/27.
 */
public class StereoOffWithCDCommand implements Command {
    Stereo stereo;

    public StereoOffWithCDCommand(Stereo stereo){
        this.stereo = stereo;
    }

    /**
     * 要实现这个请求，需要调用音响的三个方法，
     *  1)首先打开它
     *  2)设置成CD播放
     *  3)将音量设置为11
     */
    @Override
    public void execute() {
        stereo.off();
    }

    @Override
    public void undo() {
        stereo.onDefault(11);
    }
}
