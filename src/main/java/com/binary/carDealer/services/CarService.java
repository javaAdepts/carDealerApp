package com.binary.carDealer.services;


import com.binary.carDealer.dtos.CarDto;
import com.binary.carDealer.entities.Car;

import java.util.List;

public interface CarService {

    public List<Car> getAllCars();
    public Car getCarById(long id);
    public Car createCar(CarDto carDto);
    public Car updateCar(long id, CarDto carDto);
    public void deleteCarById(long id);

}
