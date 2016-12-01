package com.zhengjy.test.io.pipedInputStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道输入流的缓冲区默认大小是1024字节
 */
public class PipedStreamMain {
	public static void main(String[] args) {
		Sender sender = new Sender();
		Receiver receiver = new Receiver();
		PipedOutputStream out = sender.getPipedOutputStream();
		PipedInputStream in = receiver.getPipedInputStream();
		try {
			//管道连接
			in.connect(out);
			sender.start();
			receiver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
