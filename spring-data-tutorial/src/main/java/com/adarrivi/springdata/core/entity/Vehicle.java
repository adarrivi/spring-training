package com.adarrivi.springdata.core.entity;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Column(name = "car_class")
    private String carClass;
    @Column
    private BigDecimal price;

    Vehicle() {
        // Needed by hibernate
    }

    // Used for the new vehicles
    public Vehicle(String carClass, BigDecimal price) {
        this.carClass = carClass;
        this.price = price;
    }

    public Vehicle(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carClass);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return carClass.equals(other.carClass);
    }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", carClass=" + carClass + ", price=" + price + "]";
    }

}
