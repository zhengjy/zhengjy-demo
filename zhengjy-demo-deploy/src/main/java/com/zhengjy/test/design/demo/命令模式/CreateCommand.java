package com.zhengjy.test.design.demo.命令模式;

public class CreateCommand implements Command{
	Receiver receiver;
	public CreateCommand(Receiver receiver){
		this.receiver = receiver;
	}
	
	@Override
	public void execute() {
		receiver.action();
	}

	@Override
	public void undo() {
		receiver.unAction();
	}
	
}
