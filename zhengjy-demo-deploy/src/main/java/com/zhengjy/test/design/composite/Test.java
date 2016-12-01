package com.zhengjy.test.design.composite;

public class Test {
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode("A");
		TreeNode t2 = new TreeNode("B");
		TreeNode t3 = new TreeNode("C");
		t2.add(t3);
		t1.add(t2);
	}
}
