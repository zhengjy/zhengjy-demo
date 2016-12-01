package com.zhengjy.test.design.建造者模式;

public class ManBuilder implements PresonBuilder{
	Preson person;
	public ManBuilder() {  
		person = new Man();  
    } 
	@Override
	public void buildHead() {
		 person.setBody("建造男人的身体");  
	}

	@Override
	public void buildBody() {
		 person.setFoot("建造男人的脚");  
		
	}

	@Override
	public void buildFooter() {
		person.setHead("建造男人的头");  
	}

	@Override
	public Preson buildPreson() {
		return person;
	}

}
