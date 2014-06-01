package com.adarrivi.spring.solution.exception;

public class InvalidBookingSearchException extends RuntimeException {

    public InvalidBookingSearchException(String message) {
        super(message);
    }
}
