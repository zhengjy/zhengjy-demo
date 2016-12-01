package com.zhengjy.test.design.中介者模式;

public class ConcreteColleague2 extends Colleague {
	//通过构造参数传递中介者
	public ConcreteColleague2(Mediator mediator) {
		super(mediator);
	}
	
	//自有方法
	public void selfMethod2(){
		
	}
	
	public void depMethod2(){}

}
