package com.adarrivi.spring.exercise.client;

import com.adarrivi.spring.exercise.dto.response.ResponseStatus;
import com.adarrivi.spring.exercise.dto.response.VehicleSearchRs;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class VehicleSearchTest {

    private static final String GET_ALL_VEHICLES_URL = "http://localhost:8080/vehicles";
    private static final List<String> EXPECTED_CAR_CLASSES = Arrays.asList("EDCRMD", "DDSERD", "XXDEDR");

    private RestTemplate restTemplate = new RestTemplate();

    private ResponseEntity<VehicleSearchRs> httpResponse;
    private VehicleSearchRs vehicleSearchRs;

    @Test
    public void getAllVehicles_ReturnsOkResponse() {
        doHttpGetForGetAllVehicles();
        assertHttpResponseStatusIsOk();
        assertVehicleSearchRsStatusIsOK();
    }

    private void doHttpGetForGetAllVehicles() {
        httpResponse = restTemplate.getForEntity(GET_ALL_VEHICLES_URL, VehicleSearchRs.class);
        vehicleSearchRs = httpResponse.getBody();
    }

    private void assertHttpResponseStatusIsOk() {
        Assert.assertNotNull("Response received is null", httpResponse);
        Assert.assertEquals(HttpStatus.OK, httpResponse.getStatusCode());
    }

    private void assertVehicleSearchRsStatusIsOK() {
        Assert.assertEquals(ResponseStatus.OK, vehicleSearchRs.getStatus());
    }

    @Test
    public void getAllVehicles_ReturnsAllCarClasses() {
        doHttpGetForGetAllVehicles();
        assertAllCarClassesInResponse();
    }

    private void assertAllCarClassesInResponse() {
        List<String> carClasses = vehicleSearchRs.getCarClasses();
        Assert.assertNotNull(carClasses);
        Assert.assertEquals(EXPECTED_CAR_CLASSES.size(), carClasses.size());
        Assert.assertTrue("Unexpected cars found (or missing): expected: " + EXPECTED_CAR_CLASSES + ", found: " + carClasses, EXPECTED_CAR_CLASSES.containsAll(carClasses));
    }
}
