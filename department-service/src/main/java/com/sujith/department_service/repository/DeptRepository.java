package com.sujith.department_service.repository;

import com.sujith.department_service.model.Department;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeptRepository
{
    List<Department> departments    =   new ArrayList<>();

    public Department addDept(Department department)
    {
        departments.add(department);
        return department;
    }

    public Department findDeptById(int id)
    {
        return departments.stream()
                .filter(department -> department.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<Department> findAll()
    {
        return departments;
    }
}
