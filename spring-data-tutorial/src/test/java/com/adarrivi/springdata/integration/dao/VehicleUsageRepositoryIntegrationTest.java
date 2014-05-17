package com.adarrivi.springdata.integration.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adarrivi.springdata.core.dao.VehicleUsageRepository;
import com.adarrivi.springdata.core.entity.VehicleUsage;
import com.adarrivi.springdata.integration.GenericIntegrationTest;

public class VehicleUsageRepositoryIntegrationTest extends GenericIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleUsageRepositoryIntegrationTest.class);

    @Autowired
    private VehicleUsageRepository victim;

    private List<VehicleUsage> outputUsages;

    @Test
    public void findAll_AllUsagesAreOverZero() {
        outputUsages = victim.findAll();
        thenAllUsagesAreOverZero();
        logOutputUsages();

    }

    private void thenAllUsagesAreOverZero() {
        for (VehicleUsage vehicleUsage : outputUsages) {
            Assert.assertTrue(vehicleUsage.getTimesUsed() > 0);
        }
    }

    private void logOutputUsages() {
        for (VehicleUsage vehicleUsage : outputUsages) {
            LOGGER.debug("Result found: {}", vehicleUsage);
        }
    }

}
