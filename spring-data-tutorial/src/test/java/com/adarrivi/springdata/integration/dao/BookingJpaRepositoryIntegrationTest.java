package com.adarrivi.springdata.integration.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adarrivi.springdata.core.dao.BookingJpaRepository;
import com.adarrivi.springdata.core.dao.VehicleJpaRepository;
import com.adarrivi.springdata.core.entity.Booking;
import com.adarrivi.springdata.core.entity.Vehicle;
import com.adarrivi.springdata.integration.GenericIntegrationTest;

public class BookingJpaRepositoryIntegrationTest extends GenericIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingJpaRepositoryIntegrationTest.class);

    @Autowired
    private BookingJpaRepository victim;
    @Autowired
    private VehicleJpaRepository vehicleJpaRepository;

    private List<Booking> outputBookings;

    @Test
    public void findAll_ReturnsResultsFromRepository() {
        outputBookings = victim.findAll();
        thenOutputBookingsShouldNotBeEmpty();
        logOutputResult();
    }

    private void thenOutputBookingsShouldNotBeEmpty() {
        Assert.assertNotNull(outputBookings);
        Assert.assertFalse(outputBookings.isEmpty());
    }

    private void logOutputResult() {
        for (Booking booking : outputBookings) {
            LOGGER.debug("Result found: {}", booking);
        }
    }

    @Test
    public void findByVehicle_ReturnsWithVehicle() {
        Vehicle vehicle = vehicleJpaRepository.findOne(1);
        outputBookings = victim.findByVehicle(vehicle);
        thenOutputBookingsShouldNotBeEmpty();
        thenBookingsShouldBeOnVehicle(vehicle);
        logOutputResult();
    }

    private void thenBookingsShouldBeOnVehicle(Vehicle expectedVehicle) {
        for (Booking booking : outputBookings) {
            Assert.assertEquals(expectedVehicle, booking.getVehicle());
        }
    }
}
