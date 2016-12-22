package com.zhengjy.test.sort;

import org.junit.Test;

public class Test1 {
	Integer[] arr ={1,34,6,7};
	
	@Test
	public void test1(){
		Integer l =arr[0];
		for(int i=0;i<arr.length;i++){
 			if(l.compareTo(arr[i])<0){
				l=arr[i];
			}
		}
		
		System.out.println(l);
	}
}
