package com.zhengjy.test.design.命令模式;

public class CommandOff implements Command {
	private Tv tv;
	 public CommandOff(Tv tv) {
		 this.tv = tv;
	}
	
	@Override
	public void execute() {
		tv.turnOff();
	}

}
