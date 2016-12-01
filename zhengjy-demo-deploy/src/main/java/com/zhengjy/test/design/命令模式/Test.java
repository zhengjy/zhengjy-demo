package com.zhengjy.test.design.命令模式;

public class Test {
	public static void main(String[] args) {
		//命令接受者
		Tv tv =new Tv();
		//开机命令
		Command on = new CommandOn(tv);
		//关机命令
		Command off = new CommandOff(tv);
		//频道切换命令
		Command channel = new CommandChange(tv);
		//命令控制对象
		Control control =new Control(on,off,channel);
		
		control.turnOn();
		
		control.changeChannel();
		
		control.turnOff();
		
		
		
	}
}
