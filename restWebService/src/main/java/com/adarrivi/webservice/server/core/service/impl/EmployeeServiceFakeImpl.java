package com.adarrivi.webservice.server.core.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.adarrivi.webservice.server.core.domain.Employee;
import com.adarrivi.webservice.server.core.service.EmployeeService;

@Service
class EmployeeServiceFakeImpl implements EmployeeService {

    private Set<Employee> employees = new HashSet<>();

    @Override
    public void insert(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void update(Employee employee) {
        // Dirty trick to update the set, taking into consideration the the
        // employee 'key' is the name
        employees.remove(employee);
        employees.add(employee);
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
    public Collection<Employee> getEmployees() {
        return employees;
    }
}
