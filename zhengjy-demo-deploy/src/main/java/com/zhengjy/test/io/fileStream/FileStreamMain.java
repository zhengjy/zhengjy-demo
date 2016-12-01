package com.zhengjy.test.io.fileStream;

import java.io.*;

public class FileStreamMain {
	private static final String fileName = "C:\\file.txt";
	public static void main(String[] args) {
		testWrite();
		testRead();
	}
	private static void testWrite(){
		File file = new File(fileName);
		try {
			//创建file.txt，默认是关闭"追加模式"
			FileOutputStream fos1 = new FileOutputStream(file);
			Integer len =0;
			for(;;){
				if(len >10000){
					break;
				}
				len++;
				fos1.write(len);
			}
			fos1.close();
			//创建文件file.txt对应的FileOutputStream对象，打开"追加模式"
			FileOutputStream fos2 = new FileOutputStream(file,true);
			fos2.write("12345678901".getBytes());
			fos2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testRead(){
		File file = new File(fileName);
		try {
			FileInputStream in1 = new FileInputStream(file);
			FileInputStream in2 = new FileInputStream(file);
			//获取file.txt对应的"文件描述符"
			FileDescriptor fd = in2.getFD();
			FileInputStream in3 = new FileInputStream(fd);
			
			//测试read(),从中读取一个字节
			char c1 = (char) in1.read();
			System.out.println("c1="+c1);
			
			in1.skip(25);
			
			byte[] buf = new byte[10];
			in1.read(buf,0,buf.length);
			System.out.println("buf="+(new String(buf)));
			
			BufferedInputStream bufIn = new BufferedInputStream(in3);
			char c2 = (char) bufIn.read();
			System.out.println("c2="+c2);
			 in1.close();
			 in2.close();
			 in3.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
