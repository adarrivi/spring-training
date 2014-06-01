package com.adarrivi.spring.solution.exception;

public class InvalidNewBookingException extends RuntimeException {

    public InvalidNewBookingException(String message) {
        super(message);
    }
}
