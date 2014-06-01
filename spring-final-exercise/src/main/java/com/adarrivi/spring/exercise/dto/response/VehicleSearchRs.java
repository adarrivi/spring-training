package com.adarrivi.spring.exercise.dto.response;

import java.util.List;

public class VehicleSearchRs {

    private ResponseStatus status;
    private String errorMessage;

    private List<String> carClasses;

    private VehicleSearchRs() {
        //Needed by Json parser
    }

    private VehicleSearchRs(ResponseStatus status, String errorMessage, List<String> carClasses) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.carClasses = carClasses;
    }

    public static VehicleSearchRs createErrorResponse(String errorMessage) {
        return new VehicleSearchRs(ResponseStatus.ERROR, errorMessage, null);
    }

    public static VehicleSearchRs createResponse(List<String> carClasses) {
        return new VehicleSearchRs(ResponseStatus.OK, null, carClasses);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getCarClasses() {
        return carClasses;
    }
}
