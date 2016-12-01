package com.zhengjy.test.io.pipedOutputStream;

import java.io.IOException;

public class Sender implements Runnable{
	private PipedOutPutStreamTest pout = new PipedOutPutStreamTest();
	public PipedOutPutStreamTest getPipedOutPutStreamTest(){
		return pout;
	}
	
	@Override
	public void run() {
		try {
			write();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void write() throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("0123456789");
		// 通过for循环写入1020个字节
		for(int i=0;i<102;i++){
			sb.append("0123456789");
		}
		// 再写入26个字节。
		sb.append("abcdefghijklmnopqrstuvwxyz");
		String str = sb.toString();// str的总长度是1020+26=1046个字节
		for(int i=0;i<str.length();i++){
			pout.write(i);
		}
	}

}
