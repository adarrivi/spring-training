package com.adarrivi.spring.exercise.dto.response;

public class NewBookingRs {

    private ResponseStatus status;
    private String errorMessage;

    private NewBookingRs() {
        //Needed by Json
    }

    private NewBookingRs(ResponseStatus status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static NewBookingRs createOkResponse() {
        return new NewBookingRs(ResponseStatus.OK, null);
    }

    public static NewBookingRs createErrorResponse(String errorMessage) {
        return new NewBookingRs(ResponseStatus.ERROR, errorMessage);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
