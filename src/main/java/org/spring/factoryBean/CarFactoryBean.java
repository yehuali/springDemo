package org.spring.factoryBean;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean<Car> {
    private String carInfo;

    public String getCarInfo() {
        return carInfo;
    }
    //接收逗号分隔的属性设置信息
    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    @Override
    public Car getObject() throws Exception {
            Car car = new Car();
            String[] infos = carInfo.split(",");
            car.setBrand(infos[0]);
            car.setMaxSpeed(infos[1]);
            car.setColor(infos[2]);
            return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
