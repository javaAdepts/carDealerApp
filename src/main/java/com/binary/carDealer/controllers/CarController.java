package com.binary.carDealer.controllers;

import com.binary.carDealer.dtos.CarDto;
import com.binary.carDealer.entities.Car;
import com.binary.carDealer.services.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/")
    public String home(){
        return "Welcome to Car Dealer Application";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        return  new ResponseEntity<>(carService.getCarById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
   public ResponseEntity<Car> createCar( @Valid @RequestBody CarDto carDto){
        return new ResponseEntity<>(carService.createCar(carDto), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody CarDto carDto){
        return new ResponseEntity<>(carService.updateCar(id, carDto), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        carService.deleteCarById(id);
        return new ResponseEntity<>("Car deleted with "+ id, HttpStatus.ACCEPTED);
    }


}
