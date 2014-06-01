package com.adarrivi.spring.exercise.client;

import com.adarrivi.spring.exercise.dto.request.NewBookingRq;
import com.adarrivi.spring.exercise.dto.response.NewBookingRs;
import com.adarrivi.spring.exercise.dto.response.ResponseStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestClientConfig.class})
public class BookingCreationTest {

    private static final String NEW_BOOKING_URL = "http://localhost:8080/bookings";
    private static final String EXISTING_CAR_CLASS = "EDCRMD";
    private static final String NOT_EXISTING_CAR_CLASS = "XXXXXX";


    @Autowired
    private RestTemplate restTemplate;

    private NewBookingRq newBookingRq;
    private ResponseEntity<NewBookingRs> httpResponse;
    private NewBookingRs newBookingRs;

    @Test
    public void newBooking_NullReservationNumber_ReturnsErrorResponse() {
        newBookingRq = new NewBookingRq(null, EXISTING_CAR_CLASS, new Date());
        doHttpPostForCreateNewBooking();
        assertHttpResponseStatusIsOk();
        assertNewBookingResponseStatusIs(ResponseStatus.ERROR);
    }

    private void doHttpPostForCreateNewBooking() {
        httpResponse = restTemplate.postForEntity(NEW_BOOKING_URL, newBookingRq, NewBookingRs.class);
        newBookingRs = httpResponse.getBody();
    }

    private void assertHttpResponseStatusIsOk() {
        Assert.assertNotNull("Response received is null", httpResponse);
        Assert.assertEquals(HttpStatus.OK, httpResponse.getStatusCode());
    }

    private void assertNewBookingResponseStatusIs(ResponseStatus expectedStatus) {
        Assert.assertEquals(expectedStatus, newBookingRs.getStatus());
    }

    @Test
    public void newBooking_NullCarClass_ReturnsErrorResponse() {
        newBookingRq = new NewBookingRq(getRandomUniqueString(), null, new Date());
        doHttpPostForCreateNewBooking();
        assertHttpResponseStatusIsOk();
        assertNewBookingResponseStatusIs(ResponseStatus.ERROR);
    }

    private String getRandomUniqueString() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void newBooking_NullDate_ReturnsErrorResponse() {
        newBookingRq = new NewBookingRq(getRandomUniqueString(), EXISTING_CAR_CLASS, null);
        doHttpPostForCreateNewBooking();
        assertHttpResponseStatusIsOk();
        assertNewBookingResponseStatusIs(ResponseStatus.ERROR);
    }

    @Test
    public void newBooking_CarClassNotFound_ReturnsErrorResponse() {
        newBookingRq = new NewBookingRq(getRandomUniqueString(), NOT_EXISTING_CAR_CLASS, new Date());
        doHttpPostForCreateNewBooking();
        assertHttpResponseStatusIsOk();
        assertNewBookingResponseStatusIs(ResponseStatus.ERROR);
    }

    @Test
    public void newBooking_CarClassFound_ReturnsOKResponse() {
        newBookingRq = new NewBookingRq(getRandomUniqueString(), EXISTING_CAR_CLASS, new Date());
        doHttpPostForCreateNewBooking();
        assertHttpResponseStatusIsOk();
        assertNewBookingResponseStatusIs(ResponseStatus.OK);
    }

}
