package com.zhengjy.test.design.demo.命令模式.t2;

public class LightOnCommand implements Command{
	Light light;
	public LightOnCommand(Light light){
		this.light = light;
	}
	@Override
	public void execute() {
		light.on();
	}
	
	
}
