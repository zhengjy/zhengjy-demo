package com.zhengjy.test.io.byteArrayOutStream;

import org.junit.Test;


public class ByteArrayOutStreamMain {
	ByteArrayOutStreamTest bais = new ByteArrayOutStreamTest();
	byte[] buff = new byte[ArrayLetters.length];
	private static final byte[] ArrayLetters = {1,23,5,6,7,8,9};
	
	@Test
	public void test1() throws Exception{
		bais.write(33);
		System.out.println(bais);
	}
}
