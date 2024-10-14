package com.invenna.testcode.employee.service.impl;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.EmployeeStatus;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.repositories.DbConvertor;
import com.invenna.testcode.employee.repositories.EmployeeRepository;
import com.invenna.testcode.employee.service.SearchService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchByEmployeeStatusService implements SearchService {

  private final EmployeeRepository employeeRepository;

  private final DbConvertor dbConvertor;

  @Override
  public Collection<Employee> searchEmployees(Search search) {
    return retrieveEmployeesByEmployeeStatus(search.getEmployeeStatus());
  }

  private Collection<Employee> retrieveEmployeesByEmployeeStatus(
      EmployeeStatus employeeStatus) {
    return employeeRepository.findAllByEmployeeStatus(employeeStatus)
        .stream().map(dbConvertor::fromEmployeeDbWrapper).toList();
  }


}
