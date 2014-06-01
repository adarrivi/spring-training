package com.adarrivi.spring.solution.core.dao;

import com.adarrivi.spring.solution.core.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleJpaRepository extends JpaRepository<Vehicle, Integer> {

    @Query("from Vehicle where carClass = :carClass")
    Vehicle findByCarClass(@Param("carClass") String carClass);

}
