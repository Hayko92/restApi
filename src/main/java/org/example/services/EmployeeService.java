package org.example.services;

import org.example.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(int id) ;


    public List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);

    void deleteEmployee(int id);
}