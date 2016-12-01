package com.zhengjy.test.io.objectStream;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class ObjectStreamMain {
	private static final String temp = "C:\\1.txt";
	public static void main(String[] args) throws Exception {
		testWrite();
		testRead();
	}
	
	private static void testWrite(){
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(temp));
			oos.writeBoolean(true);
			oos.writeByte((byte)16);
			oos.writeChar('a');
			oos.writeInt(123);
			oos.writeFloat(1.2F);
			oos.writeDouble(124.23);
			
			HashMap map = new HashMap();
            map.put("one", "red");
            map.put("two", "green");
            map.put("three", "blue");
            oos.writeObject(map);
			
			Box box = new Box(1,2,"xxx");
			oos.writeObject(box);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testRead() throws Exception{
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(temp));
		try {
			System.out.printf("boolean:%b\n" , ois.readBoolean());
            System.out.printf("byte:%d\n" , (ois.readByte()&0xff));
            System.out.printf("char:%c\n" , ois.readChar());
            System.out.printf("int:%d\n" , ois.readInt());
            System.out.printf("float:%f\n" , ois.readFloat());
            System.out.printf("double:%f\n" , ois.readDouble());
			HashMap map = (HashMap) ois.readObject();
			Iterator it = map.entrySet().iterator();
			while(it.hasNext()){
				Entry en = (Entry) it.next();
				System.out.println(en.getKey()+","+en.getValue());
			}
			Box box = (Box) ois.readObject();
			System.out.println(box);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ois.close();
			
		}
	}
	
	
	
}
