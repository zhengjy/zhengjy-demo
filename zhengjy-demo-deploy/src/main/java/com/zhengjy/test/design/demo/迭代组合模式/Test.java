package com.zhengjy.test.design.demo.迭代组合模式;

public class Test {
	public static void main(String[] args) {
		MenuComponent pancakeHouseMenu = new Menu("pancake house menu", "Breakfast");
		
		MenuComponent dinerMenu = new Menu("diner menu", "lunch");
		
		MenuComponent cafeMenu = new Menu("cafe menu", "dinner");
		
		MenuComponent dessertMenu = new Menu("dessert menu", "dessert of course");
		
		MenuComponent allMenus = new Menu("all menu","all menus combined");
		
		allMenus.add(pancakeHouseMenu);
		allMenus.add(dinerMenu);
		allMenus.add(cafeMenu);
		
		dinerMenu.add(new MenuItem("pasta", " diner menu child one",true,3.89));
		
		dinerMenu.add(dessertMenu);
		
		dinerMenu.add(new MenuItem("apple pie", " diner menu child two",true,1.89));
		
		Waitress waitress = new Waitress(allMenus);
		waitress.printMenu();
		
	}
}
