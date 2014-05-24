package com.adarrivi.springmvc.integration.rest.controller;


import com.adarrivi.springmvc.TestMvcBuilder;
import com.adarrivi.springmvc.core.domain.Supplier;
import com.adarrivi.springmvc.integration.GenericWebIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SupplierControllerIntegrationTest extends GenericWebIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SupplierControllerIntegrationTest.class);
    private static final String WEB_SERVICE_URL = "/suppliers";
    private static final int SUPPLIER_ID = 1;

    //Change to true to see all the http logging and the controllers
    private static final boolean ENABLE_HTTP_LOG = false;


    private String responseAsString;
    private List<Supplier> allSuppliers;

    @Test
    public void insert_findAll_delete_findAll_ReturnsEmptyList() throws Exception {
        Supplier supplier = TestMvcBuilder.createSupplier(SUPPLIER_ID);
        String supplierAsJsonString = TestMvcBuilder.toJsonString(supplier);
        insertSupplier(supplierAsJsonString);

        findAll();
        assertAllSuppliersContain(supplier);

        delete(SUPPLIER_ID);

        findAll();
        assertAllSuppliersShouldBeEmpty();
    }

    private void assertAllSuppliersContain(Supplier expectedSupplier) {
        Assert.assertNotNull(allSuppliers);
        Assert.assertTrue(allSuppliers.contains(expectedSupplier));
    }

    private void insertSupplier(String content) throws Exception {
        ResultActions resultActions = getMockMvc().perform(post(WEB_SERVICE_URL).contentType(MediaType.APPLICATION_JSON).content(content));
        getResponseAsString(resultActions);
    }

    private void getResponseAsString(ResultActions resultActions) throws Exception {
        if (ENABLE_HTTP_LOG) {
            resultActions.andDo(print());
        }
        responseAsString = resultActions.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

    private void findAll() throws Exception {
        ResultActions resultActions = getMockMvc().perform(get(WEB_SERVICE_URL));
        getResponseAsString(resultActions);
        allSuppliers = TestMvcBuilder.toTypedJsonObject(responseAsString, List.class, Supplier.class);
        logSuppliers();
    }

    private void logSuppliers() {
        if (allSuppliers.isEmpty()) {
            LOGGER.debug("Result: no suppliers");
        }
        allSuppliers.forEach(supplier -> LOGGER.debug("Result: {}", supplier));
    }

    private void delete(int id) throws Exception {
        ResultActions resultActions = getMockMvc().perform(MockMvcRequestBuilders.delete(WEB_SERVICE_URL + "/" + id));
        getResponseAsString(resultActions);
    }

    private void assertAllSuppliersShouldBeEmpty() {
        Assert.assertNotNull(allSuppliers);
        Assert.assertTrue(allSuppliers.isEmpty());
    }


}
