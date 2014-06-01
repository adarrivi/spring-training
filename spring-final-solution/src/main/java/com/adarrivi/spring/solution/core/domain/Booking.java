package com.adarrivi.spring.solution.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "reservation_number")
    private String reservationNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name = "pickup_date", nullable = false)
    private Date pickupDate;

    Booking() {
        // Needed by hibernate
    }

    public Booking(String reservationNumber, Vehicle vehicle, Date pickupDate) {
        this.reservationNumber = reservationNumber;
        this.vehicle = vehicle;
        this.pickupDate = pickupDate;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    @Override
    public String toString() {
        return "Booking [reservationNumber=" + reservationNumber + ", vehicle=" + vehicle + ", pickupDate=" + pickupDate + "]";
    }

}
