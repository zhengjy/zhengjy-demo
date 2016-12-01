package com.zhengjy.test.design.Bridge.t2;

public class PorkNoodle extends AbstractNoodle {
	
	public PorkNoodle(Peppery peppery) {
		super(peppery);
	}

	@Override
	public void eat() {
		System.out.println( "猪肉面条的口味："+super.peppery.style());
	}

}
