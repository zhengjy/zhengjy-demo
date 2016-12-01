package com.zhengjy.test.design.Bridge;
/**
 * 桥接模式：
 * 桥接模式就是把事物与其具体实现分开，使他们可以各自独立。桥接的用意：将抽象化与实现化解耦，使二者可以独立变化，
 * 像我们常有的JDBC桥DriverManager一样，JDBC进行数据库连接的时候，在各个数据库之间进行切换，基本不需要动
 * 太多代码，甚至丝毫不用动。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 下午3:04:45 <br/>
 */
public class Test {
	public static void main(String[] args) {
		Bridge b =new MyBridge();
		
		Sourceable s1 = new SourceSub1();
		b.setSource(s1);
		b.method();
		
		Sourceable s2 = new SourceSub2();
		b.setSource(s2);
		b.method();
	}
}
