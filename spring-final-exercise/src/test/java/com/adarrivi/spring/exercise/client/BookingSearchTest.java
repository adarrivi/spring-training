package com.adarrivi.spring.exercise.client;

import com.adarrivi.spring.exercise.dto.response.BookingSearchRs;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestClientConfig.class})
public class BookingSearchTest {

    private static final String BOOKING_SEARCH_URL = "http://localhost:8080/bookings";
    private static final String EXISTING_RESERVATION_NUMBER = "ASDF322342A32";
    private static final String NON_EXISTING_RESERVATION_NUMBER = "XXXXXXXXXXXXXX";


    @Autowired
    private RestTemplate restTemplate;

    private ResponseEntity<BookingSearchRs> httpResponse;
    private BookingSearchRs bookingSearchRs;

    @Test
    public void searchBooking_NullReservationNumber_ReturnsErrorResponse() {
        doHttpGetForBookingSearch(null);
        assertHttpResponseStatusIsOk();
        assertNewBookingResponseStatusIs(ResponseStatus.ERROR);
    }

    private void doHttpGetForBookingSearch(String reservationNumber) {
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("reservationNumber", reservationNumber);
        httpResponse = restTemplate.getForEntity(BOOKING_SEARCH_URL, BookingSearchRs.class, urlParameters);
        bookingSearchRs = httpResponse.getBody();
    }

    private void assertHttpResponseStatusIsOk() {
        Assert.assertNotNull("Response received is null", httpResponse);
        Assert.assertEquals(HttpStatus.OK, httpResponse.getStatusCode());
    }

    private void assertNewBookingResponseStatusIs(ResponseStatus expectedStatus) {
        Assert.assertEquals(expectedStatus, bookingSearchRs.getStatus());
    }

    @Test
    public void searchBooking_InvalidReservationNumber_ReturnsErrorResponse() {
        doHttpGetForBookingSearch(NON_EXISTING_RESERVATION_NUMBER);
        assertHttpResponseStatusIsOk();
        assertNewBookingResponseStatusIs(ResponseStatus.ERROR);
    }

    @Test
    public void searchBooking_ValidReservationNumber_ReturnsOkResponse() {
        doHttpGetForBookingSearch(EXISTING_RESERVATION_NUMBER);
        assertHttpResponseStatusIsOk();
        assertNewBookingResponseStatusIs(ResponseStatus.OK);
    }

    @Test
    public void searchBooking_ValidReservationNumber_ReturnsCorrectDetails() {
        doHttpGetForBookingSearch(EXISTING_RESERVATION_NUMBER);
        assertHttpResponseStatusIsOk();
        Assert.assertEquals("EDCRMD", bookingSearchRs.getCarClass());
        Assert.assertEquals(134f, bookingSearchRs.getPrice(), 0.1f);
        assertPickUpDateEqualsTo("2014-01-01 12:00:00");

    }

    private void assertPickUpDateEqualsTo(String expectedDateAsString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
        try {
            Date expectedDate = dateFormat.parse(expectedDateAsString);
            Assert.assertEquals(expectedDate, bookingSearchRs.getPickUpDate());
        } catch (ParseException e) {
            Assert.fail(e.getMessage());
        }
    }


}
