package com.adarrivi.springmvc.core.service.impl;

import com.adarrivi.springmvc.core.domain.FraudStatus;
import com.adarrivi.springmvc.core.exception.InvalidFraudRqException;
import com.adarrivi.springmvc.core.service.FraudService;
import com.adarrivi.springmvc.rest.dto.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
class FraudServiceFakeImpl implements FraudService {

    private static final List<String> SUSPICIOUS_NAMES = Arrays.asList("John", "Peter", "Ana", "David");
    private static final List<String> FRAUD_NAMES = Arrays.asList("Jordan", "Clara");

    @Override
    public FraudRs creditCardNameCheck(FraudRq request) {
        assertValidRequest(request);
        FraudStatus status = getFraudStatus(request.getCcName());
        return createOkResponse(status);
    }

    private void assertValidRequest(FraudRq request) {
        String cardholderName = request.getCcName();
        if (cardholderName == null || cardholderName.isEmpty()) {
            throw new InvalidFraudRqException("The carholder name is mandatory");
        }
    }

    private FraudStatus getFraudStatus(String cardholderName) {
        FraudStatus status = FraudStatus.VALID;
        if (SUSPICIOUS_NAMES.contains(cardholderName)) {
            status = FraudStatus.SUSPICIOUS;
        }
        if (FRAUD_NAMES.contains(cardholderName)) {
            status = FraudStatus.FRAUD;
        }
        return status;
    }

    private FraudRs createOkResponse(FraudStatus status) {
        FraudRsMeta meta = new FraudRsMeta(ResponseStatus.OK, "");
        FraudRsData data = new FraudRsData(status);
        return new FraudRs(meta, data);
    }


    @Override
    public FraudRs creditCardNumberCheck(FraudRq request) {
        //TODO To implement for the exercise!!!
        return null;
    }
}
