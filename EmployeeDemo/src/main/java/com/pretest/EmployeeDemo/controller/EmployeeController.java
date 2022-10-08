package com.pretest.EmployeeDemo.controller;

import com.pretest.EmployeeDemo.entity.Employee;
import com.pretest.EmployeeDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController

@RequestMapping("/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService empService;

    @GetMapping("/all")
    public List<Employee> fetchAllEmployees() {

        List<Employee> employee = empService.getAllEmployees();
        logger.info("getting all employees");
        return employee;

    }

    @PostMapping("/save")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {

        Employee newEmployee = empService.saveEmployee(employee);
        ResponseEntity<Employee> responseEntity = new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        logger.info("adding employee");
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> fetchEmployeeById(@PathVariable("id") int employeeId) {

        ResponseEntity<?> responseEntity = null;
        Employee employee = empService.getEmployeeById(employeeId);
        responseEntity = new ResponseEntity<>(employee, HttpStatus.OK);
        logger.info("getting employee by id");
        return responseEntity;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int employeeId) {

        ResponseEntity<String> responseEntity = null;
        empService.deleteEmployee(employeeId);
        responseEntity = new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        logger.info("deleting employee by id");
        return responseEntity;
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {

        ResponseEntity<Object> responseEntity = null;
        Employee updatedEmployee = empService.updateEmployee(employee);
        responseEntity = new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        logger.info("updating employee");
        return responseEntity;
    }
}