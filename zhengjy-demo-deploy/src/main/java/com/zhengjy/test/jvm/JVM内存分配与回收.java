package com.zhengjy.test.jvm;
/**
 * -verbose:gc -Xms200M -Xmx200M -Xmn100m --XX:+PrintGCDetails --XX:SurvivorRatio=8 -XX:PrintTenuringDistribution
 * 通过设置-Xms200m -Xmx200m 设置java堆大小为200m，不可扩展，-Xmn100M设置其中100m分配给新生代，则200-100=100m，则剩下的分配给老年代。
 * -XX:SurvivorRatio=8设置新生代中eden与survivor的空间比例是8:1
 * 
 * Author ：zhengjy <br/>
 * Create Time：2016年10月29日 上午10:08:23 <br/>
 */
public class JVM内存分配与回收 {
	public static void main(String[] args) {
		final int tenMB = 10* 1024*1024;
		byte[] alloc1,alloc2,alloc3;
		alloc1 = new byte[tenMB/5];
		alloc2 = new byte[5*tenMB];
		alloc3 = new byte[4*tenMB];
		alloc3 = null;
		alloc3 = new byte[6*tenMB];
		
	}
}
