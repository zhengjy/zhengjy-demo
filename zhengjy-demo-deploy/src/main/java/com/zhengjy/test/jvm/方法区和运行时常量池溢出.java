package com.zhengjy.test.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区和运行时常量溢出：
 * 	String.intern()是个native方法，它的作用是：如果字符串常量池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的String对象；
 * 否则，将此String对象包含的字符串添加到常量池中，并且返回这个String对象的引用。
 * 
 * --XX:PermSize=64m 最小堆大小
 * --XX:MaxPermSize=128 最大堆大小
 * Author ：zhengjy <br/>
 * Create Time：2016年10月27日 下午8:15:04 <br/>
 */
public class 方法区和运行时常量池溢出 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i=0;
		while(true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
