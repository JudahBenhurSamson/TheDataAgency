package com.invenna.testcode.employee.service.impl;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.repositories.DbConvertor;
import com.invenna.testcode.employee.repositories.EmployeeRepository;
import com.invenna.testcode.employee.service.SearchService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchByEmployeeNameService implements SearchService {

  private final EmployeeRepository employeeRepository;

  private final DbConvertor dbConvertor;

  @Override
  public Collection<Employee> searchEmployees(Search search) {
    return retrieveEmployeesByName(search.getEmployeeName());
  }

  private Collection<Employee> retrieveEmployeesByName(String name) {
    return employeeRepository.findAllByNameIgnoreCaseContaining(name)
        .stream().map(dbConvertor::fromEmployeeDbWrapper).toList();
  }

}
