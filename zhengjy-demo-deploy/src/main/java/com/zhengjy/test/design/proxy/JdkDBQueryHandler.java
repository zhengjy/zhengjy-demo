package com.zhengjy.test.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 		以DBQueryProxy为例，使用动态代理生成动态类，替代DBQueryProxy，使用JDK的动态代理生成对象。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午2:47:48
 */
public class JdkDBQueryHandler implements InvocationHandler{
	IDBQuery real = null;//主体接口
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(real == null){
			real= new DBQuery();//如果是第一次调用，则生成真实对象
		}
		return real.requert();//使用真是主体完成实际操作
	}
	
	public static IDBQuery createJdkProxy(){
		System.out.println("执行前");
		IDBQuery jdkProxy = (IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[]{IDBQuery.class},
				new JdkDBQueryHandler());
		return jdkProxy;
	}
}
