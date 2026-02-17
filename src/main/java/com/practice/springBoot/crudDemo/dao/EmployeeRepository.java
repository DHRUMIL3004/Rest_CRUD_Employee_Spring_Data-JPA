package com.practice.springBoot.crudDemo.dao;

import com.practice.springBoot.crudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
   // no need for implementatiom
}
