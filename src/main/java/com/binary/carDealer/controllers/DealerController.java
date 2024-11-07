package com.binary.carDealer.controllers;

import com.binary.carDealer.entities.Dealer;
import com.binary.carDealer.entities.Dealer;
import com.binary.carDealer.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dealer")
public class DealerController {
    @Autowired
    DealerService dealerService;

    @GetMapping("/all")
    public ResponseEntity<List<Dealer>> getAllDealers() {
        return new ResponseEntity<>(dealerService.getAllDealers(), HttpStatus.ACCEPTED);
    }


   @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable  Long id) {
        return new ResponseEntity<>(dealerService.getDealerById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/create")
    public ResponseEntity<Dealer> createDealer(@RequestBody Dealer dealer){
        return new ResponseEntity<>(dealerService.saveDealer(dealer), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Dealer> updateCar(@PathVariable Long id, @RequestBody Dealer Dealer){
        return new ResponseEntity<>(dealerService.updateDealer(id, Dealer), HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        dealerService.deleteDealer(id);
        return new ResponseEntity<>("Dealer deleted with "+ id, HttpStatus.ACCEPTED);
    }


}
