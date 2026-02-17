package com.practice.springBoot.crudDemo.dao;

import com.practice.springBoot.crudDemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
    
//Define Field for entity manager
private EntityManager entityManager;

//setUp Constructor Injection
    @Autowired
        public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
         entityManager = theEntityManager;
    }

    @Override
    public Employee findById(int theId) {
        //get Employee
         Employee theEmployee = entityManager.find(Employee.class, theId);

        //return Employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
       //save Employee
      Employee dbEmployee = entityManager.merge(theEmployee);

        //return the dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //find employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);


        //delete the employee
        entityManager.remove(theEmployee);
    }

    @Override
    public List<Employee> findAll() {

        // create Query
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);

        //Execute query and get result list
        List<Employee> employees = query.getResultList();

        //return the student
        return employees;
    }
}
