package com.adarrivi.springmvc.core.exception;

public class InvalidOperationException extends RuntimeException {
    private static final long serialVersionUID = -7034897190756786939L;

    public InvalidOperationException(String message) {
        super(message);
    }
}
