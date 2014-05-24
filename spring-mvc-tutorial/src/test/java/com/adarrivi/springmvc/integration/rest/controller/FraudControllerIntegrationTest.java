package com.adarrivi.springmvc.integration.rest.controller;


import com.adarrivi.springmvc.TestMvcBuilder;
import com.adarrivi.springmvc.core.domain.FraudStatus;
import com.adarrivi.springmvc.integration.GenericWebIntegrationTest;
import com.adarrivi.springmvc.rest.dto.FraudRq;
import com.adarrivi.springmvc.rest.dto.FraudRs;
import com.adarrivi.springmvc.rest.dto.ResponseStatus;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FraudControllerIntegrationTest extends GenericWebIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FraudControllerIntegrationTest.class);
    private static final String WEB_SERVICE_URL = "/fraud/creditcard/name";
    private static final long CC_NUMBER = 123456;
    private static final String EMPTY = "";

    //Change to true to see all the http logging and the controllers
    private static final boolean ENABLE_HTTP_LOG = false;

    private FraudRq inputRequest;
    private FraudRs outputResponse;

    @Test
    public void cardholderCheck_NullName_ReturnsKO() throws Exception {
        givenRequest(new FraudRq(null, CC_NUMBER));
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
    public void cardholderCheck_EmptyName_ReturnsKO() throws Exception {
        givenRequest(new FraudRq(EMPTY, CC_NUMBER));
        whenRequestCardHolderCheck();
        thenResponseStatusShouldBe(ResponseStatus.KO);
    }


    @Test
    public void cardholderCheck_ValidName_ReturnsOK() throws Exception {
        givenRequest(new FraudRq("Albert", CC_NUMBER));
        whenRequestCardHolderCheck();
        thenResponseStatusShouldBe(ResponseStatus.OK);
    }

    @Test
    public void cardholderCheck_Albert_isValid() throws Exception {
        givenRequest(new FraudRq("Albert", CC_NUMBER));
        whenRequestCardHolderCheck();
        thenFraudStatusShouldBe(FraudStatus.VALID);
    }

    private void thenFraudStatusShouldBe(FraudStatus expectedFraudStatus) {
        Assert.assertEquals(expectedFraudStatus, outputResponse.getData().getFraud());
    }

    @Test
    public void cardholderCheck_John_isSuspicious() throws Exception {
        givenRequest(new FraudRq("John", CC_NUMBER));
        whenRequestCardHolderCheck();
        thenFraudStatusShouldBe(FraudStatus.SUSPICIOUS);
    }

    @Test
    public void cardholderCheck_Clara_isFraud() throws Exception {
        givenRequest(new FraudRq("Clara", CC_NUMBER));
        whenRequestCardHolderCheck();
        thenFraudStatusShouldBe(FraudStatus.FRAUD);
    }

}
