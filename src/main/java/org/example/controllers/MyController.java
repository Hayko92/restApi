package org.example.controllers;

import org.example.entity.Employee;
import org.example.exeptionHandling.EmployeeIncorrectData;
import org.example.exeptionHandling.NoSuchEmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.services.EmployeeService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> showAllEmployees() throws IOException {
        List<Employee> allEmployees = employeeService.getAllEmployees();

        return allEmployees;
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("there is no Emploee with id:" + id + " in database");
        }
        return employee;
    }



}



