package com.zhengjy.test.design.Bridge.t2;

public class Test {
	public static void main(String[] args) {
		AbstractNoodle an = new PorkNoodle(new PepperyStyle());
		an.eat();
		AbstractNoodle an2 = new PorkNoodle(new PlainStyle());
		an2.eat();
	}
}
