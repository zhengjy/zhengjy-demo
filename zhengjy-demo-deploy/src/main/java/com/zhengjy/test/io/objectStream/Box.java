package com.zhengjy.test.io.objectStream;

import java.io.Serializable;

public class Box implements Serializable{
	private int width;
	private int height;
	private String name;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Box(int width, int height, String name) {
		super();
		this.width = width;
		this.height = height;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Box [width=" + width + ", height=" + height + ", name=" + name
				+ "]";
	}
	
}
