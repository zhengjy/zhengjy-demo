package com.zhengjy.test.design.observer.t6;

public class CurrentConditionsDisplay implements Observer,DisplayElement{
	private float temp,humidity,pressure;
	public Subject subject;
	public CurrentConditionsDisplay(Subject weatherData){
		this.subject = weatherData;
		subject.registerObserver(this);
	}
	
	@Override
	public void display() {
		System.out.println("temp="+temp+"，humidity="+humidity);
	}

	@Override
	public void update(float temp, float humidity, float pressure) {
		this.temp=temp;
		this.humidity=humidity;
		display();
	}

}
