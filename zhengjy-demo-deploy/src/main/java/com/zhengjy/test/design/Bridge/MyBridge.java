package com.zhengjy.test.design.Bridge;

public class MyBridge extends Bridge{
	public void method(){
		getSource().method();
	}
}
