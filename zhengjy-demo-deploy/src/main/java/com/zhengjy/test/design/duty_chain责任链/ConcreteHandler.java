package com.zhengjy.test.design.duty_chain责任链;
/**
 * 具体处理者角色
 * Author ：zhengjy <br/>
 * Create Time：2016年9月22日 下午2:37:26 <br/>
 */
public class ConcreteHandler extends Handler{
	/**
	 * 处理方法，调用次方法出来请求
	 */
	@Override
	public void handleRequest() {
		/**
		 * 判断是否有后继的责任对象
		 * 如果有，就转发请求给后继对象
		 * 如果没有，则处理对象
		 */
		if(getSuccessor() != null){
			System.out.println("有后继责任对象，放过请求");
			getSuccessor().handleRequest();
		}else{
			System.out.println("无后继责任对象，处理请求");
		}
	}

}
