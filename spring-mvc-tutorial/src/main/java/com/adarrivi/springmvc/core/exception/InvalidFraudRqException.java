package com.adarrivi.springmvc.core.exception;

public class InvalidFraudRqException extends RuntimeException {
    private static final long serialVersionUID = -345657190756786939L;

    public InvalidFraudRqException(String message) {
        super(message);
    }
}
