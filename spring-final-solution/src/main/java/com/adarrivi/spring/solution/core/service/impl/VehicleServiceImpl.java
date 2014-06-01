package com.adarrivi.spring.solution.core.service.impl;

import com.adarrivi.spring.exercise.dto.response.VehicleSearchRs;
import com.adarrivi.spring.solution.core.dao.VehicleJpaRepository;
import com.adarrivi.spring.solution.core.domain.Vehicle;
import com.adarrivi.spring.solution.core.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleJpaRepository vehicleJpaRepository;

    @Override
    public VehicleSearchRs getAllVehicles() {
        List<Vehicle> allVehicles = vehicleJpaRepository.findAll();
        List<String> allCarClasses = allVehicles.stream().map(vehicle -> vehicle.getCarClass()).collect(Collectors.toList());
        return VehicleSearchRs.createResponse(allCarClasses);
    }
}
