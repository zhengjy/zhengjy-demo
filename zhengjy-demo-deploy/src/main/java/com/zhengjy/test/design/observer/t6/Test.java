package com.zhengjy.test.design.observer.t6;

public class Test {
	public static void main(String[] args) {
		//创建一个气象天气对象主题
		WeatherData wd = new WeatherData();
		//创建一个画布注册到主题中
		CurrentConditionsDisplay cc = new CurrentConditionsDisplay(wd);
		//发布气象，会通知观察者
		wd.setMeasurements(1, 2, 3);
		wd.setMeasurements(4, 5, 6);
		wd.setMeasurements(7, 8, 9);
		
	}
}
