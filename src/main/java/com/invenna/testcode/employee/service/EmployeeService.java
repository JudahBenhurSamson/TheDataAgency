package com.invenna.testcode.employee.service;

import com.invenna.testcode.employee.models.Employee;

public interface EmployeeService {

  Employee retrieveEmployeeById(long id);

  Employee update(long id, Employee employee);

  Employee create(Employee employee);

  void delete(long id);


}
