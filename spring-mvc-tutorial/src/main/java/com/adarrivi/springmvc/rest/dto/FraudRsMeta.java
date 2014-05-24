package com.adarrivi.springmvc.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class FraudRsMeta {

    private ResponseStatus status;
    private String message;


    FraudRsMeta() {
        //Needed by json
    }

    public FraudRsMeta(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
