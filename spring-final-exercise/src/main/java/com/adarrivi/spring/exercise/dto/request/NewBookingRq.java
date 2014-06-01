package com.adarrivi.spring.exercise.dto.request;

import java.util.Date;

public class NewBookingRq {

    private String reservationNumber;
    private String carClass;
    private Date pickUpDate;

    private NewBookingRq() {
        //Needed by Json
    }

    public NewBookingRq(String reservationNumber, String carClass, Date pickUpDate) {
        this.reservationNumber = reservationNumber;
        this.carClass = carClass;
        this.pickUpDate = pickUpDate;
    }
}
