package com.adarrivi.springdata.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adarrivi.springdata.core.entity.Vehicle;

public interface VehicleJpaRepository extends JpaRepository<Vehicle, Integer> {

}
