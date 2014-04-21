package com.adarrivi.webservice.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adarrivi.webservice.server.core.domain.Employee;
import com.adarrivi.webservice.server.core.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class RestfulEmployeeController {

    private static final String JSON_XML_CONTENT_TYPES = "Content-Type=application/json, application/xml";
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @RequestMapping(method = RequestMethod.POST, headers = { JSON_XML_CONTENT_TYPES, "Accept=application/json, application/xml" })
    @ResponseStatus(HttpStatus.CREATED)
    public void insertEmployee(@RequestBody Employee newEmployee) {
        employeeService.insert(newEmployee);
    }

    @RequestMapping(method = RequestMethod.PUT, headers = { JSON_XML_CONTENT_TYPES, "Accept=*/*" })
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}", headers = "Accept=*/*")
    public void deleteItem(@PathVariable int id) {
        employeeService.delete(id);
    }
}
