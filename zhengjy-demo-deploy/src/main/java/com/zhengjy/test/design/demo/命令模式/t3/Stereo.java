package com.zhengjy.test.design.demo.命令模式.t3;

public class Stereo {
	
	public void on(){
		System.out.println("Stereo.on");
	}
	public void off(){
		System.out.println("Stereo.off");
	}
	public void setCD(){
		System.out.println("Stereo.setCD");
	}
	public void setDvd(){
		System.out.println("Stereo.setDvd");
	}
	public void setRedio(){
		System.out.println("Stereo.setRedio");
	}
	public void setVolume(int i){
		System.out.println("Stereo.setVolume="+i);
	}
	
}
