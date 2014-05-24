package com.adarrivi.springmvc.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FraudRq {

    private String ccName;
    private long cardNumber;


    FraudRq() {
        //Needed for Json parsers
    }

    public FraudRq(String ccName, long cardNumber) {
        this.ccName = ccName;
        this.cardNumber = cardNumber;
    }

    public String getCcName() {
        return ccName;
    }


    public long getCardNumber() {
        return cardNumber;
    }

}
