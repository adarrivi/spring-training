package com.adarrivi.springdata.integration.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adarrivi.springdata.core.dao.VehicleJpaRepository;
import com.adarrivi.springdata.core.entity.Vehicle;
import com.adarrivi.springdata.integration.GenericIntegrationTest;

public class VehicleJpaRepositoryIntegrationTest extends GenericIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleJpaRepositoryIntegrationTest.class);

    @Autowired
    private VehicleJpaRepository victim;

    private List<Vehicle> outputVehicles;

    @Test
    public void findVechilesCheaperThan_Zero_ReturnsEmpty() {
        outputVehicles = victim.findVechilesCheaperThan(BigDecimal.ZERO);
        thenOutputVehiclesShouldBeEmpty();
    }

    private void thenOutputVehiclesShouldBeEmpty() {
        Assert.assertNotNull(outputVehicles);
        Assert.assertTrue(outputVehicles.isEmpty());
    }

    @Test
    public void findVechilesCheaperThan_200_ReturnsOne() {
        BigDecimal topPrice = BigDecimal.valueOf(200);
        outputVehicles = victim.findVechilesCheaperThan(topPrice);
        thenOutputVehiclesShouldBeUnder(topPrice);
    }

    private void thenOutputVehiclesShouldBeUnder(BigDecimal topPrice) {
        Assert.assertFalse(outputVehicles.isEmpty());
        for (Vehicle vehicle : outputVehicles) {
            LOGGER.debug("vehicle under price {} : {}", topPrice, vehicle);
            Assert.assertTrue(topPrice.compareTo(vehicle.getPrice()) > 0);
        }
    }

}
