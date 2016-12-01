package com.zhengjy.test.design.demo.命令模式;

public class Test {
	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command command = new CreateCommand(receiver);
		Invoker invoker = new Invoker(command);
		invoker.runCommand();
		invoker.unDoCommand();
		
	}
}
