package com.zhengjy.test.design.visitor;
/**
 * 访问者模式:
 * 	该模式适用场景：如果我们想为一个现有的类增加新功能，不得不考虑几个事情：
 * 	1、新功能会不会与现有的功能出现兼容问题，
 * 	2、会不会在需要添加
 * 	
 * Subject是老的接口，Visitor是新的接口。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月22日 下午4:43:30 <br/>
 */
public class Test {
	public static void main(String[] args) {
		Visitor visitor = new MyVisitor();
		Subject sub = new MySubject();
		visitor.visit(sub);
	}
}
