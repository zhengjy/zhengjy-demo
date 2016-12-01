package com.zhengjy.test.io.pipedOutputStream;

import com.zhengjy.test.io.pipedInputStream.PipedInputStreamTest;

import java.io.IOException;

public class Receiver implements Runnable {
	private PipedInputStreamTest pin = new PipedInputStreamTest();
	
	public PipedInputStreamTest getPipedInputStreamTest(){
		return pin;
	}
	
	@Override
	public void run() {
		readMes();
	}
	
	private void readMes(){
		byte[] b = new byte[1024];
		int total =0;
		while(true){
			int len = pin.read();
			total +=len;
			System.out.println("len---->"+len);
			System.out.println(total);
			if(total>b.length){
				break;
			}
			
		}
		try {
			pin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
