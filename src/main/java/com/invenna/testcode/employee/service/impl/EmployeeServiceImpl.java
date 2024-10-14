package com.invenna.testcode.employee.service.impl;

import com.invenna.testcode.employee.entities.DepartmentDbWrapper;
import com.invenna.testcode.employee.entities.EmployeeDbWrapper;
import com.invenna.testcode.employee.exception.NotFoundException;
import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.EmployeeStatus;
import com.invenna.testcode.employee.models.JoiningDateRange;
import com.invenna.testcode.employee.models.SalaryRange;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.repositories.DbConvertor;
import com.invenna.testcode.employee.repositories.DepartmentRepository;
import com.invenna.testcode.employee.repositories.EmployeeRepository;
import com.invenna.testcode.employee.service.EmployeeService;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  private final DepartmentRepository departmentRepository;

  private final DbConvertor dbConvertor;

  @Override
  public Employee retrieveEmployeeById(long id) {
    EmployeeDbWrapper employeeDbWrapper = getEmployeeDbWrapper(id);
    return dbConvertor.fromEmployeeDbWrapper(employeeDbWrapper);
  }

  private EmployeeDbWrapper getEmployeeDbWrapper(long id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Employee not found for this id :" + id));
  }

  @Override
  public Employee update(long id, Employee employee) {
    EmployeeDbWrapper fromDb = getEmployeeDbWrapper(id);
    DepartmentDbWrapper departmentDbWrapper = getDepartmentDbWrapper(employee);
    fromDb.setDepartment(departmentDbWrapper);
    fromDb.setName(employee.getName());
    fromDb.setEmployeeStatus(employee.getEmployeeStatus());
    fromDb.setSalary(employee.getSalary());
    fromDb.setJoiningDate(employee.getJoiningDate());
    return dbConvertor.fromEmployeeDbWrapper(
        employeeRepository.save(fromDb));
  }

  @Override
  public Employee create(Employee employee) {
    EmployeeDbWrapper employeeDbWrapper = dbConvertor.toEmployeeDbWrapper(employee);
    DepartmentDbWrapper departmentDbWrapper = getDepartmentDbWrapper(employee);
    if (departmentDbWrapper != null) {
      employeeDbWrapper.setDepartment(departmentDbWrapper);
    }
    return dbConvertor.fromEmployeeDbWrapper(
        employeeRepository.save(employeeDbWrapper));
  }

  private DepartmentDbWrapper getDepartmentDbWrapper(Employee employee) {
    DepartmentDbWrapper departmentDbWrapper = null;
    if (employee.getDepartment() != null) {
      departmentDbWrapper = departmentRepository.findById(
          employee.getDepartment().getId()).orElse(null);
      if (departmentDbWrapper != null) {
        departmentDbWrapper.setName(employee.getDepartment().getName());
      }
    }
    return departmentDbWrapper;
  }

  @Override
  public void delete(long id) {
    EmployeeDbWrapper fromDb = getEmployeeDbWrapper(id);
    employeeRepository.delete(fromDb);
  }


}
