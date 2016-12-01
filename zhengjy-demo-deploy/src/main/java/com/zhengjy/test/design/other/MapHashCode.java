package com.zhengjy.test.design.other;
/**
 *  map hashCode值算法
 * @author zhengjy
 * @version 一
 * @date 2016年6月18日 下午2:30:59
 */
public class MapHashCode {
	public static void main(String[] args) {
		int a=129;
		int b=128;
	//	System.out.println("a 和b 与的结果是："+(b&10));
		System.out.println("4".hashCode());
		
		int[] arr = new int [999999];
		
		for(int i=0;i<arr.length;i+=3){//展开循环，一个循环体完成原来3个循环的工作
			arr[i] =i;
			arr[i+1] =i+1;
			arr[i+2] =i+2;
		}
		
	}
	
	public native int hashCode();
	
	public static int hash(int h){
		h ^= (h >>> 20) ^ (h >>>12);
		
		return h ^ (h>>>7) ^ (h >>>4);
	}
}
