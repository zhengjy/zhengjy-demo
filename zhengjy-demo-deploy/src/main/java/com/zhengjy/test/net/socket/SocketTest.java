package com.zhengjy.test.net.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("time-a.timefreq.bldrdoc.gov",13);
			InputStream is = s.getInputStream();
			Scanner scanner =new Scanner(is);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
