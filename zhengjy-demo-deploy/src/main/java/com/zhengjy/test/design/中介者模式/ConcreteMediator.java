package com.zhengjy.test.design.中介者模式;
/**
 * 具体中介者角色
 * 	具体中介者角色通过协调各同事角色实现协作行为，因此他必须依赖各个同事角色。
 * 
 * Author ：zhengjy <br/>
 * Create Time：2016年11月1日 下午3:46:16 <br/>
 */
public class ConcreteMediator extends Mediator {

	@Override
	public void doSomething1() {
		super.concreteColleague1.selfMethod1();
		super.concreteColleague2.selfMethod2();
	}

	@Override
	public void doSomething2() {
		super.concreteColleague1.depMethod1();
		super.concreteColleague2.depMethod2();
	}

}
