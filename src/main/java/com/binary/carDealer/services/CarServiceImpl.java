package com.binary.carDealer.services;

import com.binary.carDealer.exceptions.CarNotFoundException;
import com.binary.carDealer.dtos.CarDto;
import com.binary.carDealer.entities.Car;
import com.binary.carDealer.entities.Dealer;
import com.binary.carDealer.mappers.CarMapper;
import com.binary.carDealer.repositories.CarRepository;
import com.binary.carDealer.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements  CarService{

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DealerRepository dealerRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    @Override
    public Car getCarById(long id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if(optionalCar.isPresent()){
            return optionalCar.get();
        }
          throw  new CarNotFoundException("Car not found with id: "+ id +" in our system");
    }

    @Override
    public Car createCar(CarDto carDto) {

        Dealer savedDealer = null;
        Car car = CarMapper.carDtoToCar(carDto);

        if (car.getDealer() != null && car.getDealer().getDealerId() == 0) {
//       check weather dealer is null or not, if dealer exits then check id, if id is 0 then save the dealer and get the id;
            savedDealer = dealerRepository.save(car.getDealer());
            car.setDealer(savedDealer);
        }
        Car savedCar = carRepository.save(car);

        return savedCar;
    }

    @Override
    public Car updateCar(long id, CarDto carDto) {

        Optional<Car> optionalCar = carRepository.findById(id);

        if(optionalCar.isPresent()){
            Car car = CarMapper.carDtoToCar(carDto);
            Car updatedCar = optionalCar.get();

            if(car.getBrand() != null)
                updatedCar.setBrand(car.getBrand());
            if(car.getModel() != null)
                updatedCar.setModel(car.getModel());
            if(car.getColor() != null)
                updatedCar.setColor(car.getColor());
            if(car.getPrice() >0)
                updatedCar.setPrice(car.getPrice());
            if(car.getYear() >0)
                updatedCar.setYear(car.getYear());
            if(car.getDealer() != null)
                updatedCar.setDealer(car.getDealer());

            return carRepository.save(updatedCar);
        }
        throw  new CarNotFoundException("Car not found with id: "+ id +" in our system");
    }

    @Override
    public void deleteCarById(long id) {
        Optional<Car> optionalCar = carRepository.findById(id);

        if(optionalCar.isPresent()){
            carRepository.deleteById(id);
        }

        throw  new CarNotFoundException("Car not found with id: "+ id +" in our system");
    }
}
