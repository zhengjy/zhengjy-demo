package com.zhengjy.test.design.建造者模式.t2;
/**
 * 建造者模式场景:
 * 	兑吧特殊App定制首页的展示区块，App1和App2分别 根据自己的需求展示不同的区块逻辑。
 * 调用者无需关心整个首页的区块是怎么构件出来的，只需要调用对应的App构件工具，返还app定制的区块。
 * 	建造者模式最主要的功能是基本方法的调用顺序安排，也就是这些基本方法已经实现了，通俗地说就是零件的装配，顺序不同产生的对象也不同；
 * 
 * Author ：zhengjy <br/>
 * Create Time：2016年11月2日 上午8:44:01 <br/>
 */
public class Test {
	public static void main(String[] args) {
		CutisDirector cutisDirector = new CutisDirector();
		cutisDirector.setCutis(new App1Impl(new Cutis()) );
		Cutis cutis = cutisDirector.getApp1();
		System.out.println("--------App1定制--------");
		System.out.println(cutis.getIcon());
		System.out.println(cutis.getBanner());
		System.out.println(cutis.getGoods());
		System.out.println(cutis.getShowcase());
		
		System.out.println("--------App2定制--------");
		cutisDirector.setCutis(new App2Impl(new Cutis()) );
		Cutis cutis2 = cutisDirector.getApp2();
		System.out.println(cutis2.getIcon());
		System.out.println(cutis2.getBanner());
		System.out.println(cutis2.getGoods());
		System.out.println(cutis2.getShowcase());
		
	}
}
