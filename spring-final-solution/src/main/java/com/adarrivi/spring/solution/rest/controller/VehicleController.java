package com.adarrivi.spring.solution.rest.controller;

import com.adarrivi.spring.exercise.dto.response.VehicleSearchRs;
import com.adarrivi.spring.solution.core.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public VehicleSearchRs getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test done";
    }

}
