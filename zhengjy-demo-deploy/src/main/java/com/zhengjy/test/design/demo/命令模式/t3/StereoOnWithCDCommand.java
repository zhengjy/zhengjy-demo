package com.zhengjy.test.design.demo.命令模式.t3;

import com.zhengjy.test.design.demo.命令模式.t2.Command;

public class StereoOnWithCDCommand implements Command {
	Stereo stereo;
	public StereoOnWithCDCommand(Stereo stereo) {
		this.stereo = stereo;
	}
	
	@Override
	public void execute() {
		stereo.on();
		stereo.setCD();
		stereo.setVolume(11);
	}

}
