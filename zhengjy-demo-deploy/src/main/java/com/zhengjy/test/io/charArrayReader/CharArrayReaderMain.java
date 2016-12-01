package com.zhengjy.test.io.charArrayReader;

import java.io.CharArrayReader;

public class CharArrayReaderMain {
	private static final char[] ArrayLetters = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private static final int LEN =5;
	public static void main(String[] args) throws Exception {
		testCharArrayReader();
	}
	private static void testCharArrayReader() throws Exception{
		//创建CharArrayReader字符流，内容是ArrayLetters
		CharArrayReader car =new CharArrayReader(ArrayLetters);
		
		//从字符数组流中读取5个字符
		for(int i=0;i<LEN;i++){
			//若能读取下一个字符，则读取下一个字符
			if(car.ready()){
				//读取下一个字符
				char tmp  = (char) car.read();
				System.out.printf("%d : %c\n", i, tmp);
			}
		}
		
		//若该字符流不支持标记功能，则直接退出
		if(!car.markSupported()){
			return ;
		}
		
		/*
		 * 标记字符流中下一个读取的位置。即--标记"f",因为前面已经读取了5个字符，所以下一个读取的位置是第6个字符
		 * （1）CharArrayReader类的mark(0)函数参数0是没有实际意义的。
		 * （2）mark()与reset()会将字符流中下一个被读取的位置重置未mark()中所保留的位置
		 */
		car.mark(0);
		//跳过5个字符。跳过5个字符后，字符流中下一个被读取的值就是"k";
		car.skip(5);
		//从字符流中读取5个数据。既读取"klmno"
		char[] buf= new char[LEN];
		car.read(buf,0,LEN);
		System.out.printf("buf=%s\n",String.valueOf(buf));
		
		//重置字符流，既，将字符流中下一个被读取的位置重置到mar所标记的位置，既f
		car.reset();
		//从重置后的字符流中读取5个自负到buf中，既读取fghij
		car.read(buf,0,LEN);
		System.out.printf("buf=%s\n", String.valueOf(buf));
	}
	
	
}














