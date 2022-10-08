package com.pretest.EmployeeDemo.service;

import com.pretest.EmployeeDemo.entity.Employee;
import com.pretest.EmployeeDemo.exception.EmployeeNotFoundException;
import com.pretest.EmployeeDemo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeServiceImpl implements EmployeeService
{
    @Autowired
    transient private EmployeeRepo empRepo;

    Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Getting Employees list");
        List<Employee> employees=empRepo.findAll();
        logger.info(" Successfull search of all employees");
        return employees;
    }

    @Override
    public Employee saveEmployee(Employee employee)
    {
        logger.info("Adding Employees ");
        Employee savedEmployee=empRepo.save(employee);
        logger.info("Employee added succesfully");
        return savedEmployee;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        logger.info("Getting employee by id");
        Optional<Employee> optionalEmployee=empRepo.findById(employeeId);
        if(optionalEmployee==null)
        {
            throw new EmployeeNotFoundException("Employee does not exits with id:"+employeeId);
        }
        Employee employee=optionalEmployee.get();
        logger.info("Successfully got employee by id");
        return employee;

    }

    @Override
    public void deleteEmployee(int employeeId) {
        logger.info("Deleting employee ");
        Optional<Employee> optionalEmployee=empRepo.findById(employeeId);
        if (optionalEmployee==null)
        {
            throw new EmployeeNotFoundException("Employee doest not exits with id:"+employeeId);
        }
        Employee employee=optionalEmployee.get();
        empRepo.delete(employee);
        logger.info("Employee Deleted Succesfully");
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        logger.info("Update employee ");
        Optional<Employee> optionalEmployee=empRepo.findById(employee.getId());
        if(optionalEmployee==null)
        {
            throw new EmployeeNotFoundException("Employee doest not exits with id:"+employee.getId());
        }
        Employee updateEmployee=empRepo.save(employee);
        logger.info("Employee Updated succesfully ");
        return updateEmployee;
    }
}