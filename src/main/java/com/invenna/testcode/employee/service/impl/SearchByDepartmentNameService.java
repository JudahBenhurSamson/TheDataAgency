package com.invenna.testcode.employee.service.impl;

import com.invenna.testcode.employee.entities.DepartmentDbWrapper;
import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.repositories.DbConvertor;
import com.invenna.testcode.employee.repositories.DepartmentRepository;
import com.invenna.testcode.employee.repositories.EmployeeRepository;
import com.invenna.testcode.employee.service.SearchService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchByDepartmentNameService implements SearchService {

  private final EmployeeRepository employeeRepository;

  private final DepartmentRepository departmentRepository;

  private final DbConvertor dbConvertor;

  @Override
  public Collection<Employee> searchEmployees(Search search) {
    return retrieveEmployeesByDepartment(search.getDepartmentName());
  }

  private Collection<Employee> retrieveEmployeesByDepartment(String department) {
    Collection<DepartmentDbWrapper> departmentDbWrappers = departmentRepository.findByNameIgnoreCaseContaining(
        department);
    return departmentDbWrappers.stream().map(
            departmentDbWrapper -> employeeRepository.findAllByDepartmentId(
                departmentDbWrapper.getId()))
        .flatMap(Collection::stream)
        .map(dbConvertor::fromEmployeeDbWrapper)
        .toList();
  }

}
