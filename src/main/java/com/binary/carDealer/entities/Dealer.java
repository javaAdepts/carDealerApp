package com.binary.carDealer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dealerId;
    private String dealerName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Car> cars;

    public Dealer() {

    }

    public Dealer( String dealerName, List<Car> cars) {
        this.dealerName = dealerName;
        this.cars = cars;
    }

    public long getDealerId() {
        return dealerId;
    }

    public void setDealerId(long dealerId) {
        this.dealerId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "dealerId=" + dealerId +
                ", dealerName='" + dealerName + '\'' +
                ", cars=" + cars +
                '}';
    }
}
