package com.adarrivi.spring.solution.core.service.impl;

import com.adarrivi.spring.exercise.dto.request.NewBookingRq;
import com.adarrivi.spring.exercise.dto.response.BookingSearchRs;
import com.adarrivi.spring.exercise.dto.response.NewBookingRs;
import com.adarrivi.spring.solution.core.dao.BookingJpaRepository;
import com.adarrivi.spring.solution.core.dao.VehicleJpaRepository;
import com.adarrivi.spring.solution.core.domain.Booking;
import com.adarrivi.spring.solution.core.domain.Vehicle;
import com.adarrivi.spring.solution.core.service.BookingService;
import com.adarrivi.spring.solution.exception.InvalidBookingSearchException;
import com.adarrivi.spring.solution.exception.InvalidNewBookingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingJpaRepository bookingJpaRepository;
    @Autowired
    private VehicleJpaRepository vehicleJpaRepository;

    @Override
    @Transactional
    public NewBookingRs createBooking(NewBookingRq request) {
        assertValidNewBookingRq(request);
        Vehicle vehicle = findVehicle(request.getCarClass());
        Booking newBooking = new Booking(request.getReservationNumber(), vehicle, request.getPickUpDate());
        bookingJpaRepository.save(newBooking);
        return NewBookingRs.createOkResponse();
    }

    private void assertValidNewBookingRq(NewBookingRq request) {
        String reservationNumber = request.getReservationNumber();
        if (reservationNumber == null || reservationNumber.isEmpty()) {
            throw new InvalidNewBookingException("The reservation number cannot be empty");
        }
        String carClass = request.getCarClass();
        if (carClass == null || carClass.isEmpty()) {
            throw new InvalidNewBookingException("The car class cannot be empty");
        }
        Date pickUpDate = request.getPickUpDate();
        if (pickUpDate == null) {
            throw new InvalidNewBookingException("The pickup date cannot be empty");
        }
    }

    private Vehicle findVehicle(String carClass) {
        Vehicle vehicle = vehicleJpaRepository.findByCarClass(carClass);
        if (vehicle == null) {
            throw new InvalidNewBookingException("The vehicle doesn't exist");
        }
        return vehicle;
    }

    @Override
    public BookingSearchRs findBooking(String reservationNumber) {
        assertValidReservationNumber(reservationNumber);
        Booking booking = bookingJpaRepository.findOne(reservationNumber);
        if (booking == null) {
            throw new InvalidBookingSearchException("Booking not found");
        }
        Vehicle bookingVehicle = booking.getVehicle();
        return BookingSearchRs.createResponse(bookingVehicle.getCarClass(), bookingVehicle.getPrice().floatValue(), booking.getPickupDate());

    }

    private void assertValidReservationNumber(String reservationNumber) {
        if (reservationNumber == null || reservationNumber.isEmpty()) {
            throw new InvalidBookingSearchException("The reservation number cannot be empty");
        }
    }
}
