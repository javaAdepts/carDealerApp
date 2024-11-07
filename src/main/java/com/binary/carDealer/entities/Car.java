package com.binary.carDealer.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String brand, model , color, registerNumber;
    private int year;
    @Column(name = "car_price")
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dealer")
    private Dealer dealer;

    public Car(String brand, String model, String color, String registerNumber, int year, double price, Dealer dealer) {

        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.dealer = dealer;
    }

    public Car() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", registerNumber='" + registerNumber + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", dealer=" + dealer +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Car car = (Car) o;
//        return id == car.id && year == car.year && Double.compare(price, car.price) == 0 && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(registerNumber, car.registerNumber) && Objects.equals(dealer, car.dealer);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = Long.hashCode(id);
//        result = 31 * result + Objects.hashCode(brand);
//        result = 31 * result + Objects.hashCode(model);
//        result = 31 * result + Objects.hashCode(color);
//        result = 31 * result + Objects.hashCode(registerNumber);
//        result = 31 * result + year;
//        result = 31 * result + Double.hashCode(price);
//        result = 31 * result + Objects.hashCode(dealer);
//        return result;
//    }
}
