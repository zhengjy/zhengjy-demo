package com.zhengjy.test.design.adapter.t2;

import com.zhengjy.test.design.adapter.Source;
import com.zhengjy.test.design.adapter.Targetable;

/**
 * 对象的适配器：
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 上午11:44:20 <br/>
 */
public class Test2 {
	public static void main(String[] args) {
		Targetable targetable = new Wrapper(new Source());
		targetable.method1();
		targetable.method2();
	}
}
