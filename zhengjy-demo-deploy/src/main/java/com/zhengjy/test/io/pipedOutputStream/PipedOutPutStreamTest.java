package com.zhengjy.test.io.pipedOutputStream;

import com.zhengjy.test.io.pipedInputStream.PipedInputStreamTest;

import java.io.IOException;
import java.io.OutputStream;

public class PipedOutPutStreamTest extends OutputStream{
	//与PipedOutputStream通信的PipedInputStream对象
	private PipedInputStreamTest sink;
	
	public PipedOutPutStreamTest(){
		
	}
	
	public  void connect(PipedInputStreamTest snk) throws IOException{
		
		if(snk !=null && snk.connected){
			System.out.println("已经和PipedInputStream建立了链接");
			throw new IOException("Already connected");
		}
		//设置管道输入流
		sink = snk;
		//初始化管道输入流的读写位置
		//in是PipedInputStream中定义的。代表管道输入流的读写位置
		snk.in = -1;
		//out是PipedInputStream中定义的，代表管道输出流的读写位置
		snk.out=0;
		//connected是PipedInputStream中定义的，用于标识管道输入流和管道输出流是否已经链接
		snk.connected=true;
		
	}

	@Override
	public void write(int b) throws IOException {
		if(sink == null){
			throw new IOException("Pipe not connected");
		}
		sink.receive(b);
	}
}
