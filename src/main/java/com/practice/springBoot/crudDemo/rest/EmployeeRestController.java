package com.practice.springBoot.crudDemo.rest;

import com.practice.springBoot.crudDemo.dao.EmployeeDAO;
import com.practice.springBoot.crudDemo.entity.Employee;
import com.practice.springBoot.crudDemo.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private JsonMapper jsonMapper;
    private EmployeeService employeeService;

    //quick and dirty : inject employee Services(EmployeeDAO)(use constructor injection)

    public EmployeeRestController(EmployeeService theEmployeeService, JsonMapper theJsonMapper) {
        employeeService = theEmployeeService;
       jsonMapper =theJsonMapper;
    }


    //Expose "/employee" and return a list of employee
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee with id: " + employeeId + " not found");
        }

        return theEmployee;
    }

    @PostMapping("/employees")
        public Employee addEmployee(@RequestBody Employee theEmployee ){

        //also just in case they pass an id in json .... set id to 0
        //this is to force a save of new item ... instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //add mapping for put /update
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    @PatchMapping("/employees/{employeeId}")
    public Employee updateEmployee(@RequestBody Map<String,Object> patchPayLoad, @PathVariable int employeeId) {
        Employee dbEmployee =employeeService.findById(employeeId);

        if (dbEmployee==null){
            throw new RuntimeException("Employee with id: " + employeeId + " not found");
        }

        if(patchPayLoad.containsKey("id")){
            throw new RuntimeException("Employee id not allowed in Request body " + employeeId);
        }

        //apply the partial update
        Employee patchEmployee = jsonMapper.updateValue(dbEmployee,patchPayLoad);



        return employeeService.save(dbEmployee);
    }

    @DeleteMapping("employees/{employeeId}")
    public void delete(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
    }


}
