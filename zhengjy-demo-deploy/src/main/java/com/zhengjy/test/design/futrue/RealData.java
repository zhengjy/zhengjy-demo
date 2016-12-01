package com.zhengjy.test.design.futrue;
/**
 * RealData是最终需要使用的数据模型，它构造很慢。在这里，使用sleep()函数模拟这个过程
 * @author zhengjy
 * @version 一
 * @date 2016年6月18日 下午3:48:32
 */
public class RealData implements Data{
	protected  String result;
	/*
	 * 这里模拟扣积分操作
	 */
	public RealData(String para){
		System.out.println("扣积分开始begin");
		
		//RealData的构造可能很慢，需要用户等待很久，这里使sleep模拟
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<10;i++){
			sb.append(para+"-");
			try {
				//这里使用sleep，代替一个很慢的操作过程
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result=sb.toString();
		System.out.println("扣积分开始end");
	}
	
	@Override
	public String getResult() {
		return result;
	}

}
