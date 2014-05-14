package com.adarrivi.springdata.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String carClass;
    @Column
    private float price;

    Vehicle() {
        // Needed by hibernate
    }

    public Vehicle(String carClass, float price) {
        this.carClass = carClass;
        this.price = price;
    }

    public String getCarClass() {
        return carClass;
    }

    public float getPrice() {
        return price;
    }

}
