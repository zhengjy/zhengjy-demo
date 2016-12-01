package com.zhengjy.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出：
 * 	检查堆参数设置（-Xmx与-Xms）,与机器物理内存对比看是否还可以调大，从代码上检查是否存在某些对象生命周期过长、持有状态时间过长的情况，尝试减少程序运行
 * 期间的内存消耗。
 * 	-Xmx20m：设置JVM最大可用内存为20M
 *  -Xms20m：设置JVM最小内存未20M
 * Author ：zhengjy <br/>
 * Create Time：2016年10月27日 下午5:57:14 <br/>
 */
public class Java堆内存溢出异常测试 {
	static class OOMObject{}
	
	public static void main(String[] args) {
		List<OOMObject> os = new ArrayList<OOMObject>();
		while(true){
			os.add(new OOMObject());
		}
	}
}
