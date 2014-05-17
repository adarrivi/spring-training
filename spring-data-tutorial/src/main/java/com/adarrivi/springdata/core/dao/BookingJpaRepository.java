package com.adarrivi.springdata.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarrivi.springdata.core.entity.Booking;
import com.adarrivi.springdata.core.entity.Vehicle;

public interface BookingJpaRepository extends JpaRepository<Booking, String> {

    List<Booking> findByVehicle(Vehicle vehicle);

}
