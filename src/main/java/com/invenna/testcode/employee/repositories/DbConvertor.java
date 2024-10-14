package com.invenna.testcode.employee.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invenna.testcode.employee.entities.DepartmentDbWrapper;
import com.invenna.testcode.employee.entities.EmployeeDbWrapper;
import com.invenna.testcode.employee.models.Department;
import com.invenna.testcode.employee.models.Employee;
import org.springframework.stereotype.Component;

@Component
public class DbConvertor {

  private final ObjectMapper objectMapper;

  public DbConvertor(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public EmployeeDbWrapper toEmployeeDbWrapper(Employee employee) {
    EmployeeDbWrapper employeeDbWrapper = new EmployeeDbWrapper();
    employeeDbWrapper.setName(employee.getName());
    employeeDbWrapper.setSalary(employee.getSalary());
    employeeDbWrapper.setJoiningDate(employee.getJoiningDate());
    employeeDbWrapper.setEmployeeStatus(employee.getEmployeeStatus());
    employeeDbWrapper.setDepartment(toDepartmentWrapper(employee.getDepartment()));
    return employeeDbWrapper;
  }

  public Employee fromEmployeeDbWrapper(EmployeeDbWrapper employeeDbWrapper) {
    return objectMapper.convertValue(employeeDbWrapper, Employee.class);
  }


  public DepartmentDbWrapper toDepartmentWrapper(Department department) {
    DepartmentDbWrapper departmentDbWrapper = new DepartmentDbWrapper();
    departmentDbWrapper.setName(department.getName());
    departmentDbWrapper.setId(department.getId());
    return departmentDbWrapper;
  }
}

