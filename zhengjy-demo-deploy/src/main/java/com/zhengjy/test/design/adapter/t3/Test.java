package com.zhengjy.test.design.adapter.t3;
/**
 * 接口的适配器模式
 * 有时我们写的接口有多个方法，当我们写该接口的实现类时，必须实现改接口的所有方法，这样是比较浪费的。
 * 因为并不是所有的方法都是我们需要的，有时候只需要某些，为了解决这个问题，我们引入接口的适配器模式，
 * 借助于一个抽象类，该抽象类实现了接口所有方法，而我们并不和原始的接口打交道，只和该抽象类取得联系，
 * 所以我们写一个类，继承该抽象类，重写我们需要的方法就行。
 * 
 * 步骤：定义一个抽象类实现接口所有方法，需要那个方法定义一个类继承抽象方法即可。
 * 
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 下午1:57:33 <br/>
 */
public class Test {
	public static void main(String[] args) {
		Sourceable s = new SourceSub1();
		Sourceable s2 = new SourceSub2();
		s.method1();
		s.method2();
		s2.method1();
		s2.method2();
		
	}
}
