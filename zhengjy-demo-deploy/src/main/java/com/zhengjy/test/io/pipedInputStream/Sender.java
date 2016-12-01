package com.zhengjy.test.io.pipedInputStream;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * 发送者
 * @author zhengjy
 * @version 一
 * @date 2016年8月14日 上午10:10:21
 */
public class Sender extends Thread{
	/*
	 * 管道输出流对象
	 * 他和管道输入流PipedInputStream对象绑定，
	 * 从而可以将数据发送给管道输入流的数据，然后用户就可以从管道输入流读取数据。
	 */
	private PipedOutputStream po = new PipedOutputStream();
	
	//获取管道输出流对象
	public PipedOutputStream getPipedOutputStream(){
		return po;
	}
	
	@Override
	public void run(){
		//writeShortMessage();
		writeLongMessage();
	}

	private void writeLongMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("0123456789");
		// 通过for循环写入1020个字节
		for(int i=0;i<102;i++){
			sb.append("0123456789");
		}
		// 再写入26个字节。
		sb.append("abcdefghijklmnopqrstuvwxyz");
		String str = sb.toString();// str的总长度是1020+26=1046个字节
		try {
			// 将1046个字节写入到“管道输出流”中
			po.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			po.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeShortMessage() {
		String str = new String("x1234567890");
		try {
			po.write(str.getBytes());
			po.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

















































