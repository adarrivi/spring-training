package com.adarrivi.spring.solution.core.dao;

import com.adarrivi.spring.solution.core.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingJpaRepository extends JpaRepository<Booking, String> {

}
