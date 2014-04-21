package com.adarrivi.webservice.server.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.adarrivi.webservice.server.core.domain.Employee;
import com.adarrivi.webservice.server.core.service.EmployeeService;

@Service
class EmployeeServiceFakeImpl implements EmployeeService {

    private List<Employee> employees = new ArrayList<>();

    @Override
    public void insert(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void update(Employee employee) {
        int employeeIndex = employees.indexOf(employee);
        if (employeeIndex != -1) {
            employees.add(employeeIndex, employee);
        }
    }

    @Override
    public void delete(int id) {
        Employee toRemove = findEmployeeById(id);
        if (toRemove != null) {
            employees.remove(toRemove);
        }
    }

    private Employee findEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        return employees;
    }
}
