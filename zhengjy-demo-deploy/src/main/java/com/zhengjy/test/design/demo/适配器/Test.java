package com.zhengjy.test.design.demo.适配器;

public class Test {
	public static void main(String[] args) {
		Duck duck = new MallardDuck();
		Turkey turkey = new WildTurkey();

		System.out.println("-----------wildTurkey--------------");
		turkey.gobble();
		turkey.fly();
			
		System.out.println("---------MallardDuck-----------");
		duck.quack();
		duck.fly();
		
		System.out.println("----------------TurkeyAdapter---------------");
		Duck duck2 = new TurkeyAdapter(turkey);
		duck2.quack();
		duck2.fly();
		
		
	}
}
