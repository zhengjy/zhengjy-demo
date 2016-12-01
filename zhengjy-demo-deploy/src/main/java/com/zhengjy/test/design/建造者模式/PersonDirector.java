package com.zhengjy.test.design.建造者模式;
public class PersonDirector {
	public Preson constructPerson(PresonBuilder builder){
		builder.buildHead();
		builder.buildBody();
		builder.buildFooter();
		return builder.buildPreson();
		
	}
}
