package com.binary.carDealer.dtos;

import com.binary.carDealer.entities.Dealer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CarDto {

    @NotNull(message = "Brand should not be null")
    private String brand;
    @NotNull(message = "Model should not be null")
    private String model ;
    @NotNull(message = "Color should not be null")
    private String color;
    private String registerNumber;
    @Min(value = 1990, message = "Year should not be less than 1990" )
    @Max(value = 2024, message = "Year should not be greater than current year")
    private int year;
    @Positive(message =  "Price should should have a positive value")
    private double price;
    private Dealer dealer;

     public CarDto() {

     }

    public CarDto(String brand, String model, String color, String registerNumber, int year, double price, Dealer dealer) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this.year = year;
        this.price = price;
        this.dealer = dealer;
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
        return "CarDto{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", registerNumber='" + registerNumber + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", dealer=" + dealer +
                '}';
    }
}
