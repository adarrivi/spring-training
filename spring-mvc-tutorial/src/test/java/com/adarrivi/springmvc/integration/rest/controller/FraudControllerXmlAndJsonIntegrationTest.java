package com.adarrivi.springmvc.integration.rest.controller;


import com.adarrivi.springmvc.TestMvcBuilder;
import com.adarrivi.springmvc.core.domain.FraudStatus;
import com.adarrivi.springmvc.integration.GenericWebIntegrationTest;
import com.adarrivi.springmvc.rest.dto.FraudRq;
import com.adarrivi.springmvc.rest.dto.FraudRs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FraudControllerXmlAndJsonIntegrationTest extends GenericWebIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FraudControllerXmlAndJsonIntegrationTest.class);
    private static final String WEB_SERVICE_URL = "/fraud/creditcard/name";
    private static final long CC_NUMBER = 123456;
    private static final String VALID_NAME = "Ender";

    //Change to true to see all the http logging and the controllers
    private static final boolean ENABLE_HTTP_LOG = false;

    private FraudRq inputRequest;
    private FraudRs outputResponse;

    private String requestAsString;
    private MediaType mediaType;
    private String responseAsString;

    private Jaxb2Marshaller jaxb2Marshaller;

    @Before
    public void setUp() {
        super.setUp();
        jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(FraudRq.class, FraudRs.class);
    }

    @Test
    public void requestValidJson() throws Exception {
        givenRequest(new FraudRq(VALID_NAME, CC_NUMBER));
        givenRequestAsJsonString();
        whenRequestCardHolderCheck();
        outputResponse = TestMvcBuilder.toJsonObject(responseAsString, FraudRs.class);
        thenFraudStatusShouldBe(FraudStatus.VALID);
    }

    private void givenRequest(FraudRq request) {
        inputRequest = request;
    }

    private void givenRequestAsJsonString() {
        requestAsString = TestMvcBuilder.toJsonString(inputRequest);
        mediaType = MediaType.APPLICATION_JSON;
    }

    private void whenRequestCardHolderCheck() throws Exception {
        LOGGER.debug("Request as string: {}", requestAsString);
        ResultActions resultActions = getMockMvc().perform(get(WEB_SERVICE_URL).content(requestAsString).contentType(mediaType));
        if (ENABLE_HTTP_LOG) {
            resultActions.andDo(print());
        }
        responseAsString = resultActions.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        LOGGER.debug("Response as string: {}", responseAsString);
    }

    private void thenFraudStatusShouldBe(FraudStatus expectedFraudStatus) {
        Assert.assertEquals(expectedFraudStatus, outputResponse.getData().getFraud());
    }


    @Test
    public void requestValidXml() throws Exception {
        givenRequest(new FraudRq(VALID_NAME, CC_NUMBER));
        givenRequestAsXmlString();
        whenRequestCardHolderCheck();
        outputResponse = toXmlObject(responseAsString);
        thenFraudStatusShouldBe(FraudStatus.VALID);
    }

    private void givenRequestAsXmlString() {
        requestAsString = toXmlString(inputRequest);
        mediaType = MediaType.APPLICATION_XML;
    }

    public String toXmlString(Object object) {
        StringWriter writer = new StringWriter();
        jaxb2Marshaller.marshal(object, new StreamResult(writer));
        return writer.toString();
    }

    public <T> T toXmlObject(String xmlString) {
        return (T) jaxb2Marshaller.unmarshal(new StreamSource(new StringReader(xmlString)));
    }

}
