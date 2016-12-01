package com.zhengjy.test.design.中介者模式;

public class ConcreteColleague1 extends Colleague {
	//通过构造参数传递中介者
	public ConcreteColleague1(Mediator mediator) {
		super(mediator);
	}
	
	//自有方法
	public void selfMethod1(){
		
	}
	
	public void depMethod1(){}

}
