package com.zhengjy.test.design.demo.模板模式;

public class Test {
	public static void main(String[] args) {
		Tea tea = new Tea();
		tea.prepareRecipe();
		System.out.println("----------------------------------");
		Caffee caffee = new Caffee();
		caffee.prepareRecipe();
	}
}
