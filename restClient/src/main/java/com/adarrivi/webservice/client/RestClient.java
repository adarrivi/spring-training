package com.adarrivi.webservice.client;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    private static final String BASE_URL = "http://localhost:8080/restWebService/services/employee";

    @Autowired
    private RestTemplate restTemplate;

    public void doRestCalls() {
        Employee newEmployee = new Employee(1, "Tyrion Lannister", 2000);
        restTemplate.postForEntity(BASE_URL, newEmployee, newEmployee.getClass());
        newEmployee.doubleSalary();
        restTemplate.put(BASE_URL, newEmployee);
        LOGGER.debug("All employees: {}", requestAllEmployees());
        restTemplate.delete(BASE_URL + "/" + newEmployee.getId());
        LOGGER.debug("All employees: {}", requestAllEmployees());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Collection<Employee> requestAllEmployees() {
        ResponseEntity<Collection> allEmployeesResponse = restTemplate.getForEntity(BASE_URL, Collection.class);
        return allEmployeesResponse.getBody();
    }
}
