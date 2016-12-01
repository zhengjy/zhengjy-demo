package com.zhengjy.test.design.proxy;

/**
 * 代理模式：
 * 		代理类DBQueryProxy是轻量级对象，创建很快，用于替换DBQUery的位置
 * 
 * 		将代理模式用于实现延迟加载，可以有效的提升系统的启动速度。
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午2:33:13
 */
public class DBQueryProxy implements IDBQuery {
	private DBQuery real = null;
	@Override
	public String requert() {
		//在真正需要的时候，才创建真实对象，创建过程可能很慢
		if(real == null){
			real = new DBQuery();
		}
		//在多线程环境下，这里返回一个虚假类，类似于Futrue模式
		return real.requert();
	}

}
