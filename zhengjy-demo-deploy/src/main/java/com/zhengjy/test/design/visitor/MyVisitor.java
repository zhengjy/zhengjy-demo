package com.zhengjy.test.design.visitor;


public class MyVisitor implements Visitor {

	@Override
	public void visit(Subject sub) {
		System.out.println("Visitor sub "+ sub.getSubject());
	}

	

}
