package com.zhengjy.test.design.demo.迭代组合模式;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent{
	ArrayList menuComponent = new ArrayList();
	String name;
	String description;
	
	public Menu(String name,String description){
		this.name = name;
		this.description= description;
	}
	
	public void add(MenuComponent component){
		menuComponent.add(component);
	}
	
	public void remove(MenuComponent component){
		menuComponent.remove(component);
	}
	
	public MenuComponent getChild(int i){
		return (MenuComponent) menuComponent.get(i);
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void print(){
		System.out.print("" +getName());
		System.out.println("，"+getDescription());
		System.out.println("------------------");
		
		Iterator iterator = menuComponent.iterator();
		while(iterator.hasNext()){
			MenuComponent conponent = (MenuComponent) iterator.next();
			conponent.print();
		}
		
	}
	
	
	public Iterator createIterator(){
		return new CompositeIterator(menuComponent.iterator());
	}
}
