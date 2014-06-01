package com.adarrivi.spring.solution.rest.controller;

import com.adarrivi.spring.exercise.dto.response.BookingSearchRs;
import com.adarrivi.spring.exercise.dto.response.NewBookingRs;
import com.adarrivi.spring.solution.exception.InvalidBookingSearchException;
import com.adarrivi.spring.solution.exception.InvalidNewBookingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(InvalidNewBookingException.class)
    public
    @ResponseBody
    NewBookingRs invalidNewBookingException(InvalidNewBookingException ex) {
        LOG.error("Invalid new booking operation: {}", ex);
        return NewBookingRs.createErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(InvalidBookingSearchException.class)
    public
    @ResponseBody
    BookingSearchRs invalidBookingSearchException(InvalidBookingSearchException ex) {
        LOG.error("Invalid new booking operation: {}", ex);
        return BookingSearchRs.createErrorResponse(ex.getMessage());
    }


}
