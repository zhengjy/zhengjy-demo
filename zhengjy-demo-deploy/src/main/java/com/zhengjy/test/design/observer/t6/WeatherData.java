package com.zhengjy.test.design.observer.t6;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{
	private List<Observer> observers = null;
	float temp, humidity, pressure;
	
	public WeatherData(){
		observers = new ArrayList<Observer>();
	}
	@Override
	public void registerObserver(Observer ovserver) {
		observers.add(ovserver);
	}

	@Override
	public void removeObserver(Observer ovserver) {
		if(observers.indexOf(ovserver)>0){
			observers.remove(observers.indexOf(ovserver));
		}
	}

	@Override
	public void notifyObservers() {
		for(Observer o :observers){
			o.update(temp, humidity, pressure);
		}
	}
	
	private void measurements(){
		notifyObservers();
	}
	
	public void setMeasurements(float temp,float humidity,float pressure){
		this.temp=temp;
		this.humidity=humidity;
		this.pressure=pressure;
		measurements();
	}

}
