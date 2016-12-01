package com.zhengjy.test.design.futrue;
/**
 * FutureData实现了一个快速返回的RealData包装，它只是一个包装，或者说是一个RealData的虚拟实现。因此，它可以很快被构造返回。
 * 当使用FutureData 的getResult()方法时，程序会阻塞，等待realData被注入到程序中，才使用realData的getResult()方法返回。
 * @author zhengjy
 * @version 一
 * @date 2016年6月18日 下午3:37:51
 */
public class FutureData implements Data {
	protected RealData realData = null;//FutureData是Real的包装
	protected boolean isReady = false;
	public synchronized void  setRealData(RealData realData) {
		if(isReady){
			return;
		}
		this.realData=realData;
		isReady = true;
		notifyAll();//RealData已经被注入，通知getResult()
	}

	@Override
	public synchronized String getResult() {//会等待RealData构造完成
		System.out.println("执行异步任务开始 begin");
		while(!isReady){
			try {
				wait();//一直等待，直到RealData被注入
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("执行异步任务结束  。。。end");
		return realData.result;//由RealData实现
	}

}
