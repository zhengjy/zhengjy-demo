package com.zhengjy.test.design.observer.t2;
/**
 * 实例描述：客户支付了订单款项，这时财务需要开具发票，出纳需要记账，配送员需要配货。
 * 抽象观察者
 * @author zhengjy
 * @version 一
 * @date 2016年6月10日 下午5:14:47
 */
public interface ISubject {
	void Notify();
}
