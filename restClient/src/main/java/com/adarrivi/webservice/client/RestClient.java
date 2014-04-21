package com.adarrivi.webservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    private static final String FAKE_BASE_URL = "http://localhost:8080/restWebService/services/fake/sampleEmployee";

    @Autowired
    private RestTemplate restTemplate;

    public void doRestCalls() {

        Employee employee = restTemplate.getForObject(FAKE_BASE_URL, Employee.class);
        LOGGER.debug("Found: {}", employee);

        // // GET ALL ITEMS and display them.
        // System.out.println("All CatalogItems:");
        // CatalogItemCollection coll = restTemplate.getForObject(BASE_URL +
        // "/items", CatalogItemCollection.class);
        // for (CatalogItem item : coll.getCatalogItems()) {
        // System.out.println("\tCatalogItem as obj:\t" + item);
        // }
        //
        // // DELETE an item.
        // restTemplate.delete(BASE_URL + "/item/2");
        // System.out.println("Deleted item 2");
    }
}
