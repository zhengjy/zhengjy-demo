package com.zhengjy.test.design.strategy;
/**
 * 策略模式：
 * 假设现在要设计一个贩卖各类书籍的购书系统。一个最简单的情况就是把所有货品的单价乘以数量，
 * 比如，本网站对高级会员每本%20价格促销；对中级会员%10价格促销；对初级会员没有。
 * Author ：zhengjy <br/>
 * Create Time：2016年9月21日 下午8:06:05 <br/>
 */
public class Test {
	public static void main(String[] args) {
		//选择并创建需要使用的策略对象
		MemberStrategy  strategy =new AdvanceMemberStrategy();
		//创建环境
		Price p = new Price(strategy);
		//计算价格
		double quote =p.quote(300);
		System.out.println("图书的最终价格为："+quote);
	}
}
