package com.adarrivi.springmvc.core.service;

import com.adarrivi.springmvc.rest.dto.FraudRq;
import com.adarrivi.springmvc.rest.dto.FraudRs;

public interface FraudService {

    FraudRs creditCardNameCheck(FraudRq request);

    FraudRs creditCardNumberCheck(FraudRq request);
}
