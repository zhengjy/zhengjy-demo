package com.zhengjy.test.design.futrue.t2;

import java.util.concurrent.Callable;

public class RealData  implements Callable<String>{
	private String para;
	public RealData(String para){
		this.para = para;
	}
	
	@Override
	public String call() throws Exception {
		//这里是真是的业务，其执行可能非常慢
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<10;i++){
			sb.append(para);
			try {
				//这里使用sleep，代替一个很慢的操作过程
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}
