package com.adarrivi.springmvc.integration.rest.controller;

import com.adarrivi.springmvc.TestMvcBuilder;
import com.adarrivi.springmvc.core.domain.FraudStatus;
import com.adarrivi.springmvc.integration.GenericWebIntegrationTest;
import com.adarrivi.springmvc.rest.dto.FraudRq;
import com.adarrivi.springmvc.rest.dto.FraudRs;
import com.adarrivi.springmvc.rest.dto.ResponseStatus;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
public class TutorialExerciseTest extends GenericWebIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TutorialExerciseTest.class);
    private static final String WEB_SERVICE_URL = "/fraud/creditcard/number";
    private static final long CC_NUMBER = 123456;
    private static final String CC_NAME = "Andrew";

    //Change to true to see all the http logging and the controllers
    private static final boolean ENABLE_HTTP_LOG = false;

    private FraudRq inputRequest;
    private FraudRs outputResponse;


    @Test
    public void ccNumberCheck_NegativeNumber_ReturnsKO() throws Exception {
        givenRequest(new FraudRq(CC_NAME, -12345));
        whenRequestCardHolderCheck();
        thenResponseStatusShouldBe(ResponseStatus.KO);
    }

    private void givenRequest(FraudRq request) {
        inputRequest = request;
    }

    private void whenRequestCardHolderCheck() throws Exception {
        String requestAsJsonString = TestMvcBuilder.toJsonString(inputRequest);
        ResultActions resultActions = getMockMvc().perform(get(WEB_SERVICE_URL).content(requestAsJsonString).contentType(MediaType.APPLICATION_JSON));
        if (ENABLE_HTTP_LOG) {
            resultActions.andDo(print());
        }
        String responseAsString = resultActions.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        LOGGER.debug("Response as string: {}", responseAsString);
        outputResponse = TestMvcBuilder.toJsonObject(responseAsString, FraudRs.class);
    }

    private void thenResponseStatusShouldBe(ResponseStatus expectedStatus) {
        Assert.assertEquals(expectedStatus, outputResponse.getMeta().getStatus());
    }

    @Test
    public void ccNumberCheck_ZeroNumber_ReturnsOK() throws Exception {
        givenRequest(new FraudRq(CC_NAME, 0));
        whenRequestCardHolderCheck();
        thenResponseStatusShouldBe(ResponseStatus.OK);
    }


    @Test
    public void ccNumberCheck_ZeroNumber_ReturnsFraud() throws Exception {
        givenRequest(new FraudRq(CC_NAME, 0));
        whenRequestCardHolderCheck();
        thenFraudStatusShouldBe(FraudStatus.FRAUD);
    }

    private void thenFraudStatusShouldBe(FraudStatus expectedFraudStatus) {
        Assert.assertEquals(expectedFraudStatus, outputResponse.getData().getFraud());
    }


    @Test
    public void ccNumberCheck_NumberBelow1000_ReturnsSuspicious() throws Exception {
        givenRequest(new FraudRq(CC_NAME, 999));
        whenRequestCardHolderCheck();
        thenFraudStatusShouldBe(FraudStatus.SUSPICIOUS);
    }

    @Test
    public void ccNumberCheck_NumberAbove1000_ReturnsValid() throws Exception {
        givenRequest(new FraudRq(CC_NAME, 1001));
        whenRequestCardHolderCheck();
        thenFraudStatusShouldBe(FraudStatus.VALID);
    }
}
