package com.zhengjy.test.design.proxy;

/***
 * 他是一个重量级对象，构造会很慢
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午2:31:26
 */
public class DBQuery implements IDBQuery {
	public DBQuery() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Override
	public String requert() {
		return "request string";
	}
	
	
}
