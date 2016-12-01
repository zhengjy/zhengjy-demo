package com.zhengjy.test.design.中介者模式;
/**
 * 抽象中介者角色
 * 	抽象中介者角色定义统一的接口，用于各同事角色之间的通讯。
 * Author ：zhengjy <br/>
 * Create Time：2016年11月1日 下午3:45:15 <br/>
 */
public abstract class Mediator {
	protected ConcreteColleague1 concreteColleague1;
	protected ConcreteColleague2 concreteColleague2;
	public ConcreteColleague1 getConcreteColleague1() {
		return concreteColleague1;
	}
	public void setConcreteColleague1(ConcreteColleague1 concreteColleague1) {
		this.concreteColleague1 = concreteColleague1;
	}
	public ConcreteColleague2 getConcreteColleague2() {
		return concreteColleague2;
	}
	public void setConcreteColleague2(ConcreteColleague2 concreteColleague2) {
		this.concreteColleague2 = concreteColleague2;
	}
	
	public abstract void doSomething1();
	public abstract void doSomething2();
}
