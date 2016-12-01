package com.zhengjy.test.io.pipedInputStream;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * 接收者线程
 * @author zhengjy
 * @version 一
 * @date 2016年8月14日 上午9:58:57
 */
public class Receiver extends Thread{
	
	/*
	 * 管道从输入流对象，
	 * 他和管道输出流PipedOutputStream对象绑定，从而可以接收管道输出流的数据，再让用户读取
	 */
	private PipedInputStream pin = new PipedInputStream();
	
	//获取管道输入流对象
	public PipedInputStream getPipedInputStream(){
		return pin;
	}
	
	
	
	 @Override
	 public void run() {
		//readMessageOnce(); 
		
		readMessageContinued(); 
	 }


	 //从管道输入流读取一次数据
	public void readMessageOnce() {
		//虽然byte是2028个字节，但管道输入流最多只读取1024个字节，因为管道输入流的缓冲区大小默认1024字节
		byte[]  b =new byte[2028];
		try {
			int len = pin.read(b);
			System.out.println(new String(b,0,len));
			pin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readMessageContinued(){
		int total =0;
		byte[] b = new byte[1024];
		try {
			while(true){
				int  len =pin.read(b);
				total +=len;
				System.out.println(new String(b,0,len));
				if(total >1024){
					break;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				pin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
			
			
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
