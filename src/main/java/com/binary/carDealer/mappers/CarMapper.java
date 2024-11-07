package com.binary.carDealer.mappers;

import com.binary.carDealer.dtos.CarDto;
import com.binary.carDealer.entities.Car;

public class CarMapper {

    public static Car carDtoToCar(CarDto carDto) {
        Car car = new Car();

            if(carDto.getBrand() != null)
                car.setBrand(carDto.getBrand());
            if(carDto.getModel() != null)
                car.setModel(carDto.getModel());
            if(carDto.getColor() != null)
                car.setColor(carDto.getColor());
            if(carDto.getPrice() >0)
                car.setPrice(carDto.getPrice());
            if(carDto.getYear() >0)
                car.setYear(carDto.getYear());
            if(carDto.getDealer() != null)
                car.setDealer(carDto.getDealer());

        return car;
    }
}
