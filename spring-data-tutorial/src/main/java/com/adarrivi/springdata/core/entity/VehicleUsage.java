package com.adarrivi.springdata.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VehicleUsage {

    @Id
    private String carClass;
    @Column
    private int timesUsed;

    VehicleUsage() {
        // Needed by hibernate
    }

    public String getCarClass() {
        return carClass;
    }

    public int getTimesUsed() {
        return timesUsed;
    }

    @Override
    public String toString() {
        return "VehicleUsage [carClass=" + carClass + ", timesUsed=" + timesUsed + "]";
    }

}
