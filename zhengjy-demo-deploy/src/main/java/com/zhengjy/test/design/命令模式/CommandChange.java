package com.zhengjy.test.design.命令模式;

public class CommandChange implements Command{
	private Tv tv;
	private int channel;
	
	 public CommandChange(Tv tv) {
		 this.tv = tv;
	}
	@Override
	public void execute() {
		tv.changeChnanel(channel);
	}

}
