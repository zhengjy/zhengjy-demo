package com.zhengjy.test.design.adapter.t2;

import com.zhengjy.test.design.adapter.Source;
import com.zhengjy.test.design.adapter.Targetable;

public class Wrapper implements Targetable {
	private Source source;
	public Wrapper(Source source){  
        super();  
        this.source = source;  
	}  
	@Override
	public void method1() {
		source.method1();
	}

	@Override
	public void method2() {
		System.out.println("this is the targetable method!");  
	}

}
