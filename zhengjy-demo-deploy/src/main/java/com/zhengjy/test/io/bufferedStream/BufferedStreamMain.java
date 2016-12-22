package com.zhengjy.test.io.bufferedStream;

import org.junit.Test;

import java.io.*;

public class BufferedStreamMain {
	private static final File f = new File("C:\\1.txt");
	private static byte[] b =  {0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,};
	@Test
	public void buuferedInputStream() throws Exception{
		InputStream bfin = new BufferedInputStream(new FileInputStream(f),2);
		for(int i=0;i<5;i++){
			//是否可以读取到下一个字节
			if(bfin.available()>=0){
				int temp = bfin.read();
				System.out.println(Integer.toHexString(temp));
			}
		}
		bfin.close();
	}
	
	@Test
	public void bufferedOutputStream() throws Exception{
		//创建BufferedOutputStream，他的缓存区大小是16，如果>=16则自动将缓冲区写入输入流。
		OutputStream os = new BufferedOutputStream(new FileOutputStream(f),10);
		os.write(b);
		os.close();
	}
}
