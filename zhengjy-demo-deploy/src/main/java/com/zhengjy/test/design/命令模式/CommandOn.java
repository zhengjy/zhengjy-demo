package com.zhengjy.test.design.命令模式;

public class CommandOn  implements Command {
	private Tv tv;
	public CommandOn(Tv tv){
		this.tv = tv;
	}
	
	@Override
	public void execute() {
		tv.turnOn();
	}

}
