package com.sujith.department_service.client;

import com.sujith.department_service.model.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmpClient
{
    @GetExchange("emp/dept/{id}")
    public List<Employee> getEmployeeByDeptId(@PathVariable int id);
}
