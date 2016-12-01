package com.zhengjy.test.io.pipedOutputStream;


import com.zhengjy.test.io.pipedInputStream.PipedInputStreamTest;

public class Test {
	public static void main(String[] args) throws Exception {
		Sender sender= new Sender();
		Receiver receiver = new Receiver();
		PipedOutPutStreamTest pout =sender.getPipedOutPutStreamTest();
		PipedInputStreamTest pin = receiver.getPipedInputStreamTest();
		pin.connect(pout);
		sender.run();
		receiver.run();
	}
}
