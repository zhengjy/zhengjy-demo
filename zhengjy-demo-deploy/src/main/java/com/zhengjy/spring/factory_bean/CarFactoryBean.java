package com.zhengjy.spring.factory_bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by zhengjy on 2017/6/11.
 */
public class CarFactoryBean implements FactoryBean<Car> {
    private String carInfo;
    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.parseInt(infos[1]));
        car.setPrice(Double.parseDouble(infos[2]));
        return car;
    }

    @Override
    public Class<Car> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
