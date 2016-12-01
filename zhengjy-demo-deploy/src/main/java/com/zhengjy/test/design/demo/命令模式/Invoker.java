package com.zhengjy.test.design.demo.命令模式;

public class Invoker {
	Command command;
	public Invoker(Command command){
		this.command = command;
	}
	
	public void runCommand(){
		command.execute();
	}
	
	public void unDoCommand(){
		command.undo();
	};
}
