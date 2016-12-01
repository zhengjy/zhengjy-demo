package com.zhengjy.test.io.BufferedInputStream;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class BufferedInputStreamTest extends FilterInputStream {
	
	protected BufferedInputStreamTest(InputStream in) {
		this(in, defaultBufferSize);
		
	}
	public BufferedInputStreamTest(InputStream in,int size){
		super(in);
		buf =new byte[size];
	}


	//BufferedInputStream 会根据“缓冲区大小”来逐次的填充缓冲区
	/*
	 * 即，BufferedInputStream填充缓冲区，用户读取缓冲区，读完之后，BufferedInputStream
	 * 会再次填充缓冲区。如此循环，直到读完数据	
	 */
	/***默认的缓冲大小是8192字节*/
	private static int defaultBufferSize = 8192;
	/**缓存数组*/
	protected volatile byte buf[];
	/*
	 * 该成员变量与buf数组的vilatile关键字共同组成了buf数组的原子更新功能实现，即，在多线程中操作BufferedInputStream
	 * 对象时，buf和bufUpdater都具有原则性（不同的线程访问到的数据都是相同的）
	 */
	/***缓存数组的原子更新器*/
	private static final AtomicReferenceFieldUpdater<BufferedInputStreamTest,byte[]> bufUpdater=
			AtomicReferenceFieldUpdater.newUpdater(BufferedInputStreamTest.class,byte[].class,
					"buf");
	//这里是指缓冲区的有效字节数，而不是输入流中的有效字节数
	/**当前缓冲区的有效字节数*/
	protected int count;
	
	//这里是指缓冲区的位置索引，而不是输入流中的位置索引
	/***当前缓冲区的位置索引*/
	protected int pos;
	
	/*
	 * markpos和reset()配合使用采用意义。操作步骤：
	 * (01) 通过mark()函数，保存pos的值到markpos中。
	 * (02) 通过reset()函数，会将pos的值重置为markpos。接着通过read()读取数据时，就会从mark()保存的位置开始读取。
	 */
	/**当前缓冲区的标记位置*/
	protected int markpos = -1;
	
	/*
	 * 
	 */
	/**marklimit是标记最大值*/
	protected int marklimit;
	
	  // 读取下一个字节
     public synchronized int read() throws IOException {
         // 若已经读完缓冲区中的数据，则调用fill()从输入流读取下一部分数据来填充缓冲区
         if (pos >= count) {
             fill();
             if (pos >= count)
                 return -1;
         }
         // 从缓冲区中读取指定的字节
         return getBufIfOpen()[pos++] & 0xff;
     }
	
     public synchronized void reset() throws IOException {
         getBufIfOpen(); // Cause exception if closed
         if (markpos < 0)
             throw new IOException("Resetting to invalid mark");
         pos = markpos;
     }
	public int read(byte[] b,int off,int len) throws IOException{
		getBufIfOpen();
		
		//读取到指定长度的数据才返回
		int n=0;
		for(;;){
			int nread = read1(b,off+n,len-n);
			if(nread <=0){
				return (n==0)? nread :n;
			}
			n+=nread;
			if(n>=len){
				return n;
			}
			InputStream input = in;
			if(input != null && input.available()<=0){
				return n;
			}
		}
		
	}
	

	/**将缓冲区的数据写入到字节数组b中，off是字节数组b的起始位置，len是写入长度
	 * @throws IOException */
	private int read1(byte[] b,int off,int len) throws IOException {
		int avail = count -pos;
		//当前缓冲区没有数据
		if(avail <=0){
			/*
			 * 加速机制。
			 * 如果读取的长度大于缓冲区的长度 并且没有markpos，则直接从原始输入流中进行读取，
			 * 从而避免无谓的COPY(从原始输入流至缓冲区，读取缓冲区全部数据，清空缓冲区，重新填入原始输入流数据)
			 */
			if(len >= getBufIfOpen().length && markpos<0){
				return getInIfOpen().read(b,off,len);
			}
			//若已经完缓冲区的数据，则调用fill()总输入流读取下一部分数据来填充缓冲区
			fill();
			
		}
		return 0;
	}

	public synchronized void mark (int readlimit) {
        marklimit = readlimit;
        markpos = pos;
    }

	private void fill() throws IOException {
		byte[] buffer = getBufIfOpen();
		if(markpos <0){//判断“输入流是否被标记”。若被标记markpos大于/等于0;否则markpos等于-1
			pos =0;
			// buffer = {1,2,3,4,5,6,7,8,9,0}
			// 2 >= buffer.length
		}else if(pos >= buffer.length){//判断buffer中有没有多余的空间
			if(markpos >0){//判断输入流有没有被标记
				int sz = pos -markpos;//获取被标记位置到buffer末尾的数据长度
				//将buffer中从markpos开始的数据拷贝到buffer中(从位置0开始填充，填充长度是sz)
				System.arraycopy(buffer, markpos, buffer, 0, sz);
				pos =sz;//将pos"被标记位置"到"buffer末尾"的数据长度
				markpos = 0;//
			
			}else if(buffer.length >= marklimit){
				markpos = -1;//取消标记
				pos = 0;//即pos=0；最后，再从输入流中读取下一部分数据到buffer中。
			}else{
				int nsz =pos*2;//
				if(nsz >marklimit){
					nsz =marklimit;
				}
				//) 新建一个字节数组nbuf。nbuf的大小是“pos*2”和“marklimit”中较小的那个数。
				byte[] nbuf = new byte[nsz];
				System.arraycopy(buffer, 0, nbuf, 0,pos);
				if(!bufUpdater.compareAndSet(this,buffer,nbuf)){
					throw new IOException("Stream closed");
				}
			}
		}
		count =pos;
		//获取输入流，然后接着从输入流中读取buffer.length个字节到buffer中
		int n = getInIfOpen().read(buffer, pos, buffer.length - pos);
		if(n>0){
			//根据从输入流中读取的实际数据的多少，来跟新buffer中数据的实际大小。
			count =n+pos;
		}
	}


	private InputStream getInIfOpen() {
		InputStream input = in;
		return input;
	}


	private byte[] getBufIfOpen() {
		byte[] buffer = buf;
		if(buffer ==null){
			return null;
		}
		return buffer;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		byte[] b ={1,2,3,4,5,6,7,8,9};
		byte[] b2= {11,12,13,14,15,16,17,18,19};
		System.arraycopy(b,2,b2, 0,2);
		for(byte bs:b2){
			System.out.println(bs);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
