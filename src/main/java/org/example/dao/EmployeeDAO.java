package org.example.dao;

import org.example.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmployees();

    void saveEmployee(Employee employee);
    public   Employee getEmployee(int id);

    void deleteEmployee(int id);
}