package com.example.employee_service.repository;

import com.example.employee_service.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpRepository
{
    List<Employee> employees    =   new ArrayList<>();

    public Employee addEmployee(Employee employee)
    {
        employees.add(employee);
        return employee;
    }

    public List<Employee> finAll()
    {
        return employees;
    }

    public Employee finEmployeeById(int id)
    {
        return employees.stream()
                .filter(employee -> employee.id() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<Employee> getEmployeesByDept(int id)
    {
        return employees.stream()
                .filter(employee -> employee.deptId() == id)
                .toList();
    }
}
