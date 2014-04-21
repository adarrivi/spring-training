package com.adarrivi.webservice.server.core.service;

import java.util.List;

import com.adarrivi.webservice.server.core.domain.Employee;

public interface EmployeeService {

    void insert(Employee employee);

    void update(Employee item);

    void delete(int id);

    List<Employee> getEmployees();
}
