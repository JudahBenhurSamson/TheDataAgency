package com.invenna.testcode.employee.service.impl;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.SalaryRange;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.repositories.DbConvertor;
import com.invenna.testcode.employee.repositories.EmployeeRepository;
import com.invenna.testcode.employee.service.SearchService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchBySalaryService implements SearchService {

  private final EmployeeRepository employeeRepository;

  private final DbConvertor dbConvertor;

  @Override
  public Collection<Employee> searchEmployees(Search search) {
      return retrieveEmployeesBySalaryRange(search.getSalaryRange());
  }

  private Collection<Employee> retrieveEmployeesBySalaryRange(SalaryRange salaryRange) {
    return employeeRepository.findAllBySalaryBetween(salaryRange.from(), salaryRange.to())
        .stream()
        .map(dbConvertor::fromEmployeeDbWrapper).toList();
  }
}
