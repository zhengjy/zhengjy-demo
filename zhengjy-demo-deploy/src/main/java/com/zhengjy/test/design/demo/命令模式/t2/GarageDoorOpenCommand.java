package com.zhengjy.test.design.demo.命令模式.t2;

public class GarageDoorOpenCommand implements Command{
	GarageDoor garageDoor;
	public GarageDoorOpenCommand(GarageDoor garageDoor){
		this.garageDoor = garageDoor;
	}
		
	
	@Override
	public void execute() {
		this.garageDoor.up();
	}

}
