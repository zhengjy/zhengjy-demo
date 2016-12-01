package com.zhengjy.test.design.proxy;

public class Test {
	public static void main(String[] args) {
		/*IDBQuery q= new DBQueryProxy();//使用代理
		System.out.println(q.requert());//在真正使用时才创建真实对象
		*/
		IDBQuery d = null;
		long begin =System.currentTimeMillis();
		
		d= JdkDBQueryHandler.createJdkProxy();//JDK动态代理
		System.out.println(System.currentTimeMillis()-begin);
		System.out.println("JDKProxy class："+d.getClass().getName());
		System.out.println(d.requert());
	}
}
