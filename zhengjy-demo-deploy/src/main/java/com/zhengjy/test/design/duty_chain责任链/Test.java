package com.zhengjy.test.design.duty_chain责任链;
/**
 * 责任链：
 * 使多个对象都有机会处理请求，从而避免请求的发送者和接受者之间的耦合关系，
 * 将这个对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理他为止。<br>
 * Author ：zhengjy <br/>
 * Create Time：2016年9月22日 下午3:01:31 <br/>
 */
public class Test {
	public static void main(String[] args) {
		//组装责任链  
        Handler handler1 = new ConcreteHandler();  
        Handler handler2 = new ConcreteHandler();  
        handler1.setSuccessor(handler2);  
        //提交请求  
        handler1.handleRequest();  
	}
}
