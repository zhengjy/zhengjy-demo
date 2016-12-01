package com.zhengjy.test.string_tokenizer;

public class StringTokenizer {
	public static void main(String[] args) {
		String orgStr= null;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<1000;i++){
			sb.append(i).append(";");
		}
		orgStr= sb.toString();
		long begin = System.currentTimeMillis();
		for(int i=0;i<1000;i++){
			orgStr.split(";");
		}
		System.out.println(System.currentTimeMillis()-begin);
		System.out.println("abc".charAt(1));
	}
}
