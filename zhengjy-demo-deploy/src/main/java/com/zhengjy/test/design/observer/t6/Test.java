package com.zhengjy.test.design.observer.t6;

public class Test {
	public static void main(String[] args) {
		WeatherData wd = new WeatherData();
		
		CurrentConditionsDisplay cc = new CurrentConditionsDisplay(wd);
		wd.setMeasurements(1, 2, 3);
		wd.setMeasurements(4, 5, 6);
		wd.setMeasurements(7, 8, 9);
		
	}
}
