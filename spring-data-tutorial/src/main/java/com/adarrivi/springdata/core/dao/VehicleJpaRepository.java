package com.adarrivi.springdata.core.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adarrivi.springdata.core.entity.Vehicle;

public interface VehicleJpaRepository extends JpaRepository<Vehicle, Integer> {

    @Query("from Vehicle where price < :topPrice ")
    List<Vehicle> findVechilesCheaperThan(@Param("topPrice") BigDecimal topPrice);

}
