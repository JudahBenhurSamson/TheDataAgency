package com.invenna.testcode.employee.service.impl;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.JoiningDateRange;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.repositories.DbConvertor;
import com.invenna.testcode.employee.repositories.EmployeeRepository;
import com.invenna.testcode.employee.service.SearchService;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchByJoiningDateService implements SearchService {

  private final EmployeeRepository employeeRepository;

  private final DbConvertor dbConvertor;

  @Override
  public Collection<Employee> searchEmployees(Search search) {
      return retrieveEmployeesByJoiningDate(search.getJoiningDateRange());
  }



  private Collection<Employee> retrieveEmployeesByJoiningDate(
      JoiningDateRange joiningDateRange) {
    return employeeRepository.findAllByJoiningDateBetween(joiningDateRange.from(),
            joiningDateRange.to())
        .stream().map(dbConvertor::fromEmployeeDbWrapper).toList();
  }

}
