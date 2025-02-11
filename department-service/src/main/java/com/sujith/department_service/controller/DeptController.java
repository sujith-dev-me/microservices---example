package com.sujith.department_service.controller;

import com.sujith.department_service.client.EmpClient;
import com.sujith.department_service.model.Department;
import com.sujith.department_service.model.Employee;
import com.sujith.department_service.repository.DeptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController
{
    private static final Logger LOGGER  = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptRepository repository;

    @Autowired
    private EmpClient empClient;

    @PostMapping
    public Department add(@RequestBody Department department)
    {
        repository.addDept(department);
        LOGGER.info("Dept added : " + department.getName());
        return department;
    }

    @GetMapping
    public List<Department> getDept()
    {
        LOGGER.info("getAllDepartments Queried");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable int id)
    {
        LOGGER.info("Dept requested for id : " + id);
        return repository.findDeptById(id);
    }

    @GetMapping("/with-emp")
    public List<Department> getDeptWithEmployees()
    {
        LOGGER.info("getAllDepartments Queried");

        List<Department> departments    =    repository.findAll();
        for(Department d: departments)
        {
            List<Employee> employees    =   empClient.getEmployeeByDeptId(d.getId());
            d.setEmployees(employees);
        }

        return departments;
    }
}
