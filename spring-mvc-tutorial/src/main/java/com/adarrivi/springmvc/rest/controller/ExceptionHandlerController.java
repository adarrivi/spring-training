package com.adarrivi.springmvc.rest.controller;

import com.adarrivi.springmvc.core.exception.InvalidFraudRqException;
import com.adarrivi.springmvc.core.exception.InvalidOperationException;
import com.adarrivi.springmvc.rest.dto.FraudRs;
import com.adarrivi.springmvc.rest.dto.FraudRsData;
import com.adarrivi.springmvc.rest.dto.FraudRsMeta;
import com.adarrivi.springmvc.rest.dto.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(Exception.class)
    public
    @ResponseBody
    ResponseEntity<String> unexpectedException(Exception ex) {
        LOG.error("Unexpected exception: " + ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public
    @ResponseBody
    ResponseEntity<String> invalidOperationException(InvalidOperationException ex) {
        LOG.error("Invalid operation: ", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFraudRqException.class)
    public
    @ResponseBody
    FraudRs invalidFraudRqException(InvalidFraudRqException ex) {
        LOG.error("Invalid fraud request: ", ex);
        FraudRsMeta meta = new FraudRsMeta(ResponseStatus.KO, ex.getMessage());
        FraudRsData data = new FraudRsData(null);
        return new FraudRs(meta, data);
    }
}
