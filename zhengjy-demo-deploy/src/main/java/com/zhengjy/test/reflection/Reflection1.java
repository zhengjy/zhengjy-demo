package com.zhengjy.test.reflection;

import java.lang.reflect.Field;


public class Reflection1 {
	public static void main(String[] args) throws Exception {
		//反射出无参的构造方法并得到对象
		Class c = User.class;
		Field idField1=c.getDeclaredField("id");
		idField1.setAccessible(true);
		System.out.println("idField1="+idField1);
		
			
		
		
		
		
		
		
		
		
		
		
		
		
		/*Method m = c.getMethod("getName", String.class);
		Object o = c.newInstance();
		m.invoke(o, "test");
		System.out.println(o.toString());
		*/
		
		
		
		
		
		
		/*Object o = c.newInstance();
		User u = (User) o;
		System.out.println(o);
		//反射出带参数的构造方法得到对象
		Constructor<User> constructor =  c.getDeclaredConstructor(int.class,String.class);
		constructor.setAccessible(true);
		Object o2 = constructor.newInstance(1,"test");
		System.out.println(o2);
*/		
		
		
		//常用的获取Class对象的3种方式：
		Class c1 = Class.forName("java.lang.String");
		
		Class c2 = String.class;	
		
		String str = "aa";
		Class classType1 = str.getClass();
		
		/*Class c = User.class;
		Field f = c.getDeclaredField("id");
		Object o = c.newInstance();
		User u = (User) o;
		System.out.println(u);
		f.setAccessible(true);
		f.set(o,1);
		System.out.println(f.get(o));*/
	}
}

class User {
	
	private int id;
	private String name;
	public  User(){}
	private User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName(String name) {
		return this.name=name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}