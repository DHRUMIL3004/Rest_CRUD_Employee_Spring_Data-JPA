package com.practice.springBoot.crudDemo.dao;

import com.practice.springBoot.crudDemo.entity.Employee;

import java.awt.*;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);
}
