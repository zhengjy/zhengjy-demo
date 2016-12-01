package com.zhengjy.test.io.byteArrayInputStream;

public class ByteArrayInputStreamTest {
	private byte buf[];//读取的数组
	private int len;//数组长度
	private int pos;// 下一个会被读取的字节的索引
	
	
	public  ByteArrayInputStreamTest(byte[] buf){
		this.buf=buf;
		this.pos = 0;
		this.len = buf.length;
	}
	
	public synchronized int read(){
		return (pos<len) ? (buf[pos++] & 0xff):-1; 
	}
	
	public synchronized int read(byte[] bt,int off,int len){
		int avail = this.len -this.pos;//控制数组下标越界，如数组里有5个元素，传入的len=6 则只取数组的长度
		if(len >avail){//
			len = avail;
		}
		if(len<=0){
			return 0;
		}
		System.arraycopy(buf,pos,bt, off,len);
		pos +=len;//下一个读取的索引赋值
		
		return len;
	}
	
	public int  available(){
		return len -pos;
	}
	
	public long skip(long n){
		long k = len -n;
		if(n<k){
			k =n<0 ? 0:n;
		}
		pos +=k;
		
		
		return k;
	}
	
	
	public static void main(String[] args) {
		System.out.println( 0& 0xff);
	}
}
