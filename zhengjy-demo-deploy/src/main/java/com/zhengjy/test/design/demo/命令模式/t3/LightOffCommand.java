package com.zhengjy.test.design.demo.命令模式.t3;

import com.zhengjy.test.design.demo.命令模式.t2.Command;
import com.zhengjy.test.design.demo.命令模式.t2.Light;

public class LightOffCommand implements Command{
	Light light ;
	@Override
	public void execute() {
		light.off();
	}

}
