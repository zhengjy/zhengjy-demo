package com.zhengjy.test.design.futrue;
/**
 * Future模式的核心在于去除了主函数中的等待时间，并使得原来需要等到的时间段可以用于处理其他的业务逻辑，从而充分的利益计算机资源。
 * @author zhengjy
 * @version 一
 * @date 2016年6月18日 下午4:44:10
 */
public class Tets {
	public static void main(String[] args) {
		Client c = new Client();
		//这里会理解返回，因为得到的是FutureData而不是RealData
		Data d = c.request("name");
		try {
			/*
			 * 如在这里生成订单，
			 */
			
			//这里可以用一个sleep代替对其他业务逻辑的处理
			//在处理这些业务逻辑的过程中，RealData被创建，从而充分利用等待的时间
		//	Thread.sleep(3000);
			System.out.println("执行主函数");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(d.getResult());
		System.out.println("---end");
	}
}
