package com.zhengjy.test.design.命令模式;

public class Control {
	private Command onCommand,offCommand,changeChannel;
	public Control(Command on, Command off, Command channel){
		this.onCommand =on;
		this.offCommand =off;
		this.changeChannel = channel;
	}
	
	public void turnOn(){
		onCommand.execute();
	}
	public void turnOff(){
		offCommand.execute();
	}
	
	public void changeChannel(){
		changeChannel.execute();
	}

}
