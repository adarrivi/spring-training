package com.adarrivi.webservice.server.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adarrivi.webservice.server.core.domain.Employee;

@RestController
@RequestMapping(value = "/fake")
public class FakeEmployeeController {

    private static final int ID = 1;
    private static final int SALARY = 100000;

    @RequestMapping(method = RequestMethod.GET, value = "/sampleEmployee", headers = "Accept=application/json, application/xml")
    public Employee getSampleEmployee() {
        return new Employee(ID, "John Smith", SALARY);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customEmployee/{name}", headers = "Accept=application/json, application/xml")
    public Employee getCustomEmployeepPathVariable(@PathVariable String name) {
        return new Employee(ID, name, SALARY);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customEmployee", headers = "Accept=application/json, application/xml")
    public Employee getCustomEmployeepParams(@RequestParam(value = "name", required = false, defaultValue = "Albert Graham") String name) {
        return new Employee(ID, name, SALARY);
    }
}
