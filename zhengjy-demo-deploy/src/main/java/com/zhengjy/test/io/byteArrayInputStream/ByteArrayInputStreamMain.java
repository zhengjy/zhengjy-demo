package com.zhengjy.test.io.byteArrayInputStream;

import org.junit.Test;

public class ByteArrayInputStreamMain {
	ByteArrayInputStreamTest bais = new ByteArrayInputStreamTest(ArrayLetters);
	byte[] buff = new byte[ArrayLetters.length];
	private static final byte[] ArrayLetters = {
        1,23,5,6,7,8,9
    };
		@Test
		public void test1(){
			for(int i=0;i<ArrayLetters.length;i++){
				int temp = bais.read();
				System.out.println(temp);
			}
		}
		@Test
		public void test2(){
			byte[] buf = new byte[ArrayLetters.length];
			for(int i=0;i<buf.length;i++){
				System.out.println(buf[i]);
			}
			bais.read(buf,0,10);
			bais.read(buf,0,6);
			for(int i=0;i<buf.length;i++){
				System.out.println(buf[i]);
			}
		}
		
		@Test
		public void test3(){
			bais.read(buff,0,6);
			System.out.println(bais.available());
			bais.read(buff,0,8);
			System.out.println(bais.available());
		}
		
		@Test
		public void test4(){
			System.out.println(bais.skip(3));
			bais.read(buff,0,6);
			for(int i=0;i<buff.length;i++){
				System.out.println(buff[i]);
			}
		}
}
