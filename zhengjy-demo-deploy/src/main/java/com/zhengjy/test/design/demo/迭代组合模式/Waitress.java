package com.zhengjy.test.design.demo.迭代组合模式;

public class Waitress {
	MenuComponent allMenus;
	
	public Waitress(MenuComponent component){
		this.allMenus = component;
	}
	
	public void printMenu(){
		allMenus.print();
	}
}
