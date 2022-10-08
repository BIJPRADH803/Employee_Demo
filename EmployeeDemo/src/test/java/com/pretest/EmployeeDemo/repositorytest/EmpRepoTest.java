package com.pretest.EmployeeDemo.repositorytest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.pretest.EmployeeDemo.entity.Employee;
import com.pretest.EmployeeDemo.repository.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class EmpRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Test
    public void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setId(2);
        employee.setFirstName("Karan");
        employee.setLastName("Singh");
        employee.setSalary(25000);
        employee.setAge(25);
        employee.setDepartment("IT");
        employee.setEmail("abc@gmail.com");
        employeeRepo.save(employee);
        assertNotNull(employee);

    }

    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("Karan");
        employee.setLastName("Singh");
        employee.setSalary(25000);
        employee.setAge(25);
        employee.setDepartment("IT");
        employee.setEmail("abc@gmail.com");
        employeeRepo.save(employee);
        assertNotNull(employee);

        Employee t = employeeRepo.save(employee);

        assertNotNull(t);
        assertEquals(employee.getFirstName(), t.getFirstName());
        assertEquals(employee.getLastName(), t.getLastName());
        assertEquals(employee.getSalary(), t.getSalary());
        assertEquals(employee.getAge(), t.getAge());
        assertEquals(employee.getDepartment(), t.getDepartment());
        assertEquals(employee.getEmail(), t.getEmail());


    }

    @Test
    public void testDeleteEmployee() {

        Employee employee = new Employee();
        employee.setId(3);
        employee.setFirstName("bijay");
        employee.setLastName("kumar");
        employee.setSalary(2000);
        employee.setAge(26);
        employee.setDepartment("IT");
        employee.setEmail("bkp@gmail.com");

        Employee t = employeeRepo.save(employee);

        employeeRepo.deleteById(t.getId());
        ;

        assertFalse(employeeRepo.existsById(1));

    }

    @Test
    public void testFindAllTrainers() {
        Employee employee = new Employee();
        employee.setId(3);
        employee.setFirstName("bijay");
        employee.setLastName("kumar");
        employee.setSalary(5000);
        employee.setAge(23);
        employee.setDepartment("cs");
        employee.setEmail("abc@gmail.com");
        employeeRepo.save(employee);
        assertNotNull(employeeRepo.findAll());
    }

}