package com.zhengjy.test.jvm;
/**
 * 栈溢出：如果线程请求的深度大于虚拟机所允许的最大深度，抛出栈溢出
 * -Xss128k ：设置每个线程栈大小。
 * Author ：zhengjy <br/>
 * Create Time：2016年10月27日 下午8:11:57 <br/>
 */
public class Java栈溢出测试 {
	private int len=1;
	
	public void stackLeak(){
		len++;
		stackLeak();
	}
	public static void main(String[] args) {
		Java栈溢出测试 j = new Java栈溢出测试();
		try{
			j.stackLeak();
		}catch(Exception e){
			System.out.println("stack len:="+j.len);
		}
	}
}
