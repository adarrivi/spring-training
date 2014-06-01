package com.adarrivi.spring.exercise.dto.response;

import java.util.Date;

public class BookingSearchRs {

    private ResponseStatus status;
    private String errorMessage;

    private String carClass;
    private Float price;
    private Date pickUpDate;

    private BookingSearchRs() {
        //Needed by Json
    }

    public BookingSearchRs(ResponseStatus status, String errorMessage, String carClass, Float price, Date pickUpDate) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.carClass = carClass;
        this.price = price;
        this.pickUpDate = pickUpDate;
    }

    public static BookingSearchRs createErrorResponse(String errorMessage) {
        return new BookingSearchRs(ResponseStatus.ERROR, errorMessage, null, null, null);
    }

    public static BookingSearchRs createResponse(String carClass, float price, Date pickUpDate) {
        return new BookingSearchRs(ResponseStatus.OK, null, carClass, price, pickUpDate);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCarClass() {
        return carClass;
    }

    public Float getPrice() {
        return price;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }
}
