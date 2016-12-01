package com.zhengjy.test.design.建造者模式;
/**
 * 建造者模式：
 * 	建造者模式是将负责的内部创建封装在内部，对于外部调用的来说，只需要传入建造者和建造工具，对于内部是如何建造成成品的，调用者无需关心。
 * 	建造者模式的优点
	（1）使用建造者模式可以使客户端不必知道产品内部组成的细节。
	（2）具体的建造者类之间是相互独立的，对系统的扩展非常有利。
	（3）由于具体的建造者是独立的，因此可以对建造过程逐步细化，而不对其他的模块产生任何影响。
 * Author ：zhengjy <br/>
 * Create Time：2016年11月1日 下午8:32:29 <br/>
 */
public class Test {
	public static void main(String[] args) {
		PersonDirector director = new PersonDirector();
		Preson preson = director.constructPerson(new ManBuilder());
		System.out.println(preson.getHead());
		System.out.println(preson.getBody());
		System.out.println(preson.getFoot());
	}
}
