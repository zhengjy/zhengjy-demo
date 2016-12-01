package com.zhengjy.test.design.demo.命令模式;

public class Receiver {
	public void action(){
		System.out.println("Receiver.action()....执行命令");
	}
	
	public void unAction(){
		System.out.println("Receiver.unAciton().....撤销命令");
	}
}
