package com.zhengjy.test.design.futrue;
/**
 * Client只有实现了获取 FutrueData,开启构造RealData的线程，并在接受请求后，很快的返回FutrueData
 * @author zhengjy
 * @version 一
 * @date 2016年6月18日 下午3:18:10
 */
public class Client {
	public Data request(final String queryStr){
		final FutureData future = new FutureData();
		new Thread(){
			public void run(){
				//RealData构建很慢，所以在单独的线程中进行
				RealData realData = new RealData(queryStr);
				future.setRealData(realData);
			}
		}.start();
		return future;
	}
}
