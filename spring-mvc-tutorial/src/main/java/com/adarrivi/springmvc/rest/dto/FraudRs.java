package com.adarrivi.springmvc.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FraudRs {

    private FraudRsMeta meta;
    private FraudRsData data;


    FraudRs() {
        //Needed for Json parsers
    }

    public FraudRs(FraudRsMeta meta, FraudRsData data) {
        this.meta = meta;
        this.data = data;
    }

    public FraudRsMeta getMeta() {
        return meta;
    }

    public FraudRsData getData() {
        return data;
    }
}
