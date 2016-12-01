package com.zhengjy.test.design.demo.命令模式.t2;

public class SimlpeRemoteContorl {
	Command command;
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public void buttonWasPressed(){
		command.execute();
	}
}
