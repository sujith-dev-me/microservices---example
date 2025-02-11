package com.example.employee_service.controller;

import com.example.employee_service.EmployeeServiceApplication;
import com.example.employee_service.model.Employee;
import com.example.employee_service.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmpController
{

    @Autowired
    private EmpRepository repository;


    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee)
    {
        return repository.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees()
    {
        return repository.finAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id)
    {
        return repository.finEmployeeById(id);
    }

    @GetMapping("/dept/{id}")
    public List<Employee> getEmployeeByDeptId(@PathVariable int id)
    {
        return repository.getEmployeesByDept(id);
    }
}
