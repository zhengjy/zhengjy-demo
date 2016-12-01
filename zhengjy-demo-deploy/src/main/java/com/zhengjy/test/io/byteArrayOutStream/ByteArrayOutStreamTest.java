package com.zhengjy.test.io.byteArrayOutStream;

import java.io.OutputStream;
import java.util.Arrays;

public class ByteArrayOutStreamTest extends OutputStream{
	private byte[] buf;//保存字节数组输出流的数据数组
	
	private int count;//字节数组输出流的数据
	
	public ByteArrayOutStreamTest(){//默认初始化长度32
		this(32);
	}
	
	public ByteArrayOutStreamTest(int len){
		buf = new byte[len];
	}
	
	/**
	 * 确保容器
	 * @param minCapacity
	 */
	private void ensureCapacity(int minCapacity){
		if(minCapacity -buf.length >0){
			grow(minCapacity);
		}
	}

	
	/**
	 * 扩展数组
	 * @param minCapacity
	 */
	private void grow(int minCapacity) {//11
		int oldCapacity =buf.length;//10
		
		int  newCapacity = oldCapacity << 1;//20
		if(newCapacity -minCapacity <0){//21
			newCapacity = minCapacity;
		}
		if (newCapacity < 0) {
           if (minCapacity < 0) // overflow
              throw new OutOfMemoryError();
           newCapacity = Integer.MAX_VALUE;
        }
		
		buf = Arrays.copyOf(buf, newCapacity);
		System.out.println(buf);
		
	}
	public synchronized void write(int b){
		ensureCapacity(count+1);
		buf[count] = (byte) b;
		count +=1;
	}
	
	public synchronized void write(byte[] b,int pos,int len){
		ensureCapacity(count+len);
		//传入的数组，传入的索引，原始的数组，原始的数组长度，传入的长度
		//  {1，2，3}      0                      {1，2，3}       3                           3
		System.arraycopy(b,pos,buf,count, len);//将传入的数组copy到原始数组中
		count +=len;
	}
	
	public static void main(String[] args) {
		System.out.println(10<<1);
	}
}
