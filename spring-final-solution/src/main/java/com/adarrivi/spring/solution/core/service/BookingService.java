package com.adarrivi.spring.solution.core.service;

import com.adarrivi.spring.exercise.dto.request.NewBookingRq;
import com.adarrivi.spring.exercise.dto.response.BookingSearchRs;
import com.adarrivi.spring.exercise.dto.response.NewBookingRs;

public interface BookingService {

    NewBookingRs createBooking(NewBookingRq request);

    BookingSearchRs findBooking(String reservationNumber);
}
