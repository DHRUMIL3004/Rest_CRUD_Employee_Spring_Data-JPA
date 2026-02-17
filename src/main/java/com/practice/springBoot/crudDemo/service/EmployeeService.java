package com.practice.springBoot.crudDemo.service;

import com.practice.springBoot.crudDemo.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();


    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);


}
