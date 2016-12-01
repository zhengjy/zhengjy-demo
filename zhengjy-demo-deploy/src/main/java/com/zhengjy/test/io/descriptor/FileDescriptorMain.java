package com.zhengjy.test.io.descriptor;

import java.io.FileDescriptor;
import java.io.FileOutputStream;

public class FileDescriptorMain {
	private static final String FileName = "file.txt";
	private static final String OutText = "Hi FileDescriptor";
	
	public static void main(String[] args) throws Exception {
		testWrite();
	}
	
	  
	private static void testWrite() throws Exception{
		FileOutputStream fos = new FileOutputStream(FileName);
		FileDescriptor fd = fos.getFD();
		FileOutputStream fos2 = new FileOutputStream(fd);
		fos.write('A');
		fos.write('a');
		 if (fd!=null){
			System.out.printf("fdout(%s) is %s\n",fd, fd.valid());
		}
		fos.close();
		fos2.close();
	}
}
