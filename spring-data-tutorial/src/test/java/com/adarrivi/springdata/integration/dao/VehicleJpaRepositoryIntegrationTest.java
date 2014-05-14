package com.adarrivi.springdata.integration.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.adarrivi.springdata.config.AppConfiguration;
import com.adarrivi.springdata.core.dao.VehicleJpaRepository;
import com.adarrivi.springdata.core.entity.Vehicle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public class VehicleJpaRepositoryIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleJpaRepositoryIntegrationTest.class);

    @Autowired
    private VehicleJpaRepository victim;

    @Test
    public void findAll() {
        List<Vehicle> allVehicles = victim.findAll();
        Assert.assertNotNull(allVehicles);
        for (Vehicle vehicle : allVehicles) {
            LOGGER.debug("Vehicle found: {}, {}", vehicle.getCarClass(), vehicle.getPrice());
        }

    }

}
