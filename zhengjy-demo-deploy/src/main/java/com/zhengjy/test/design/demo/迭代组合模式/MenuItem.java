package com.zhengjy.test.design.demo.迭代组合模式;

public class MenuItem extends MenuComponent{
	String name;
	String description;
	boolean vegetarian;
	double price;
	
	public MenuItem(String name,String description,boolean vegetarian,double price){
		this.name = name;
		this.description =description;
		this.vegetarian = vegetarian;
		this.price = price;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public double getPrice(){
		return price;
	}
	
	public boolean isVegetarian(){
		return vegetarian;
	}
	
	public void print(){
		System.out.print("" +getName());
		if(isVegetarian()){
			System.out.println("v");
		}
		System.out.println("，"+getPrice());
		System.out.println("   --"+getDescription());
	}
	
}
