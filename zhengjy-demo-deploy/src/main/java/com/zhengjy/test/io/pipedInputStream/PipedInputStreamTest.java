package com.zhengjy.test.io.pipedInputStream;

import com.zhengjy.test.io.pipedOutputStream.PipedOutPutStreamTest;

import java.io.IOException;

public class PipedInputStreamTest {
	/**管道输出流是否关闭的标记*/
	boolean closedByWriter = false;
	/**管道输入流是否关闭的标记*/
	volatile boolean closedByReader=false;
	/**管道输入流和管道输出流是否链接的标记
	  它在PipedOutputStream的connect()连接函数中被设置未ture*/
	public boolean connected = false;
	/**读取管道数据的线程*/
	Thread readSide;
	/**向管道写入数据的线程*/
	Thread writeSide;
	
	//管道的默认大小
	private static final int DEFAULT_PIPE_SIZE =1024;
	
	protected static final int PIPE_SIZE = DEFAULT_PIPE_SIZE;
	/**缓冲区*/
	protected byte buffer[];
	/**下一个写入字节的位置。in==out代表满。说明写入的数据全部读取了*/
	public int in = -1;
	/**下一个读取字节的位置。in==out代表满，说明写入的数据全部被读取了*/
	public int out = 0;
	
	public PipedInputStreamTest(){
		initPipe(DEFAULT_PIPE_SIZE);
	}

	private void initPipe(int defaultPipeSize) {
		if(defaultPipeSize <=0){
			
		}
		buffer = new byte[defaultPipeSize];
	}
	
	public void connect(PipedOutPutStreamTest pop) throws Exception{
		pop.connect(this);
	}
	
	public int read(){
		if(!connected){
			System.out.println("未和管道输出流连接");
			return -1;
		}else if(closedByReader){
			System.out.println("管道输入流已被关闭");
			return -1;
		}else if(closedByWriter){
			System.out.println("管道输出流已被关闭");
			return -1;
		}
		readSide = Thread.currentThread();//获取读取管道的线程
		int trials = 2;
		//下一个写入字节
		while(in <0){
			//管道输出流被关闭返回
			if(closedByWriter){
				return -1;
			}
			//管道写入线程不为空 && 管道写入线程是活着的 
			if((writeSide !=null) && (!writeSide.isAlive())&& (--trials<0) ){
				
			}
			notifyAll();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		int ret = buffer[out++] & 0xFF;
		//下一个读取字节的位置　>= 大于缓存区长度 
		if(out >= buffer.length){
			out =0;
		}
		//读满了
		if(in == out){
			in = -1;
		}
		
		return ret;
	}

	public void receive(int b) {
		//检查管道状态
		checkStateForReceive();
		//获取写入管道的线程
		writeSide = Thread.currentThread();
		//若写入管道的数据正好全部被读取完，则等待
		if(in==out){
			awaitSpace();
		}
		if(in<0){
			in = 0;
			out = 0;
		}
		//将b保存到缓冲区  
		buffer[in++] = (byte) (b & 0xFF);
		if(in >=buffer.length){
			in =0;
		}
		
		
	}
	
	/*
	 * 等待。
	 * 若写入管道的数据正好全部被读取完（例如，管道缓存满），则执行awaitSpace()操作；
	 * 它的目的是让读取管道的线程管道产生读取数据请求，从而才能继续的向管道中写入数据。
	 */
	private void awaitSpace() {
		/*
		 * 如果管道中读取的数据，等于写入管道的数据时，
		 * 则每隔1000ms检查管道状态，并唤醒管道操作：若有读取管道数据线程被阻塞，则唤醒该线程。
		 */
		while(in ==out){
			checkStateForReceive();
			notifyAll();
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void checkStateForReceive() {
		
	}
    public void close()  throws IOException {
        closedByReader = true;
        synchronized (this) {
            in = -1;
        }
    }
	
	
	
	
}
