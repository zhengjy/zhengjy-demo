package com.zhengjy.test.thread.threadShutdown;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供一种协作机制，设置某个“取消标识”，任务进入时检查该标志是否是取消标志，如果是那么任务将提前结束。
 * 
 * 现实场景：停止支付的示例。
 * 		银行通常都会规定如何提交一个停止支付的请求，在处理这些请求时需要做出那些响应的保证，以及当支付中断后需要遵守那些流程
 * （例如通知该事务中涉及的银行，以及对付款人的装潢进行费用评估）。这些流程和保证放在一起就构成了支票支付的取消策略。
 * 
 * 
 * 线程取消
 * @author zhengjy
 *
 */
public class PrimeGenerator implements Runnable{
	private final List<BigInteger> primes = new ArrayList<BigInteger>();
	private volatile boolean flag=false;
	@Override
	public void run() {
		BigInteger  b= BigInteger.ONE;
		while(!flag){
			b = b.nextProbablePrime();
			synchronized(this){
				primes.add(b);
			}
		}
	}
	public void cancel(){
		this.flag = true;
	}
	public synchronized List<BigInteger> get(){
		return new ArrayList<BigInteger>(primes);
	}
	
	
	List<BigInteger> aSocondOfPrimes() throws Exception{
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try{
			
			Thread.currentThread().sleep(1000);
		}finally{
			generator.cancel();
		}
		
		return generator.get();
	}
	
	public static void main(String[] args) {
	}
	
	
}
