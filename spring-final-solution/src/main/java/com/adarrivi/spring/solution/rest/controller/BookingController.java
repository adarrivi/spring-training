package com.adarrivi.spring.solution.rest.controller;

import com.adarrivi.spring.exercise.dto.request.NewBookingRq;
import com.adarrivi.spring.exercise.dto.response.BookingSearchRs;
import com.adarrivi.spring.exercise.dto.response.NewBookingRs;
import com.adarrivi.spring.solution.core.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/bookings", method = RequestMethod.POST)
    public NewBookingRs newBooking(@RequestBody NewBookingRq request) {
        return bookingService.createBooking(request);
    }

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public BookingSearchRs findBooking(@RequestParam(value = "reservationNumber") String reservationNumber) {
        return bookingService.findBooking(reservationNumber);
    }


}
