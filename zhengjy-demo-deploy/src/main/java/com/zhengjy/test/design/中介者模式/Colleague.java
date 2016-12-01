package com.zhengjy.test.design.中介者模式;
/**
 * 同事角色
 * 	每个同事角色都知道中介角色，而且与其他的同事角色通讯的时候，一定要通过中介者角色协作。每个同事类的行为分两种：一
 * Author ：zhengjy <br/>
 * Create Time：2016年11月1日 下午3:47:17 <br/>
 */
public class Colleague {
	protected Mediator mediator;
	public Colleague(Mediator mediator){
		this.mediator = mediator;
	}
}
