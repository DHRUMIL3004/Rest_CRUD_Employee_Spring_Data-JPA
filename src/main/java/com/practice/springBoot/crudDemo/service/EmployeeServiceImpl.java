package com.practice.springBoot.crudDemo.service;

import com.practice.springBoot.crudDemo.dao.EmployeeRepository;
import com.practice.springBoot.crudDemo.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {

        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }


    @Override
    public Employee findById(int theId) {
//         Optional <Employee> result= employeeRepository.findById(theId);
//
//       Employee theEmployee=null;
//        if (result.isPresent()) {
//            theEmployee= result.get();
//        }
//        else {
//            //Dont find employee throw an id
//            throw new RuntimeException("Employee not found");
//        }
//        return theEmployee;

//       return result.orElse(null);
        return employeeRepository.findById(theId)
                .orElseThrow(() -> new RuntimeException("Employee not found with this id: " + theId));

    }


    @Override
    public Employee save(Employee theEmployee) {


        return employeeRepository.save(theEmployee);
    }


    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
