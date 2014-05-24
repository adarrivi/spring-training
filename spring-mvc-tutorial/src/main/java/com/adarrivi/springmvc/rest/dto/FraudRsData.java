package com.adarrivi.springmvc.rest.dto;

import com.adarrivi.springmvc.core.domain.FraudStatus;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class FraudRsData {

    private FraudStatus fraud;

    FraudRsData() {
        //Needed by json
    }

    public FraudRsData(FraudStatus fraud) {
        this.fraud = fraud;
    }

    public FraudStatus getFraud() {
        return fraud;
    }
}
