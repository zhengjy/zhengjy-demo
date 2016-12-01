package com.zhengjy.test.net.serverSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSocket1 {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(8189);
			Socket socket = serverSocket.accept();
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			Scanner scanner = new Scanner(is);
			PrintWriter out = new PrintWriter(os,true);
			out.print("Hello!!!!");
			
			boolean flag  =false;
			while(!flag &scanner.hasNextLine()){
				String line =scanner.nextLine();
				out.print("Echo：" +line);
				if(line.trim().equals("BYE")){
					flag = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
