package com.zhengjy.test.design.demo.命令模式.t2;

public class Test {
	public static void main(String[] args) {
		Light light = new Light();
		Command command = new LightOnCommand(light);
		GarageDoor garageDoor = new GarageDoor();
		Command command2 = new GarageDoorOpenCommand(garageDoor);
		
		SimlpeRemoteContorl remote = new SimlpeRemoteContorl();
		remote.setCommand(command);
		remote.buttonWasPressed();
		remote.setCommand(command2);
		remote.buttonWasPressed();
	}
}
