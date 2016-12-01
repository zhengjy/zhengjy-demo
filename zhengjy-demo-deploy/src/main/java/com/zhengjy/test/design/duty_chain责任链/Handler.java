package com.zhengjy.test.design.duty_chain责任链;
/**
 * 抽象处理者角色
 * Author ：zhengjy <br/>
 * Create Time：2016年9月22日 下午2:38:01 <br/>
 */
public abstract class Handler {
	/**
	 * 持有后继的责任对象
	 */
	protected Handler successor;
	/**
	 * 示意处理请求的方法，可传入参数
	 * @author zhengjy
	 */
	public abstract void handleRequest();
	
	/*
	 * 取值方法
	 */
	public Handler getSuccessor() {
		return successor;
	}
	/*
	 * 赋值方法，设置后继的责任对象
	 */
	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}
	
	
	
}
