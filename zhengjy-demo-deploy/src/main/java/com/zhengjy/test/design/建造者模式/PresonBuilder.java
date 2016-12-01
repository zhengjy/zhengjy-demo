package com.zhengjy.test.design.建造者模式;

public interface PresonBuilder {
	void buildHead();
	void buildBody();
	void buildFooter();
	Preson buildPreson();
}
