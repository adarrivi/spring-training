package com.adarrivi.springmvc.rest.controller;

import com.adarrivi.springmvc.core.service.FraudService;
import com.adarrivi.springmvc.rest.dto.FraudRq;
import com.adarrivi.springmvc.rest.dto.FraudRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fraud")
public class FraudController {

    private static final String JSON = "application/json";
    private static final String XML = "application/xml";

    @Autowired
    private FraudService fraudService;

    @RequestMapping(value = "/creditcard/name", method = RequestMethod.GET, consumes = XML
            , produces = XML)
    public FraudRs xmlCreditCardNameCheck(@RequestBody FraudRq request) {
        return fraudService.creditCardNameCheck(request);
    }

    @RequestMapping(value = "/creditcard/name", method = RequestMethod.GET, consumes = JSON
            , produces = JSON)
    public FraudRs jsonCreditCardNameCheck(@RequestBody FraudRq request) {
        return fraudService.creditCardNameCheck(request);
    }


}
