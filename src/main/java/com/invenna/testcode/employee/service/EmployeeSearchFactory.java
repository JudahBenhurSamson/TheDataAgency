package com.invenna.testcode.employee.service;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.service.impl.SearchByDepartmentNameService;
import com.invenna.testcode.employee.service.impl.SearchByEmployeeNameService;
import com.invenna.testcode.employee.service.impl.SearchByEmployeeStatusService;
import com.invenna.testcode.employee.service.impl.SearchByJoiningDateService;
import com.invenna.testcode.employee.service.impl.SearchBySalaryService;
import java.util.Collection;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeSearchFactory {

  private final SearchByEmployeeNameService searchByEmployeeNameService;
  private final SearchByDepartmentNameService searchByDepartmentNameService;
  private final SearchByEmployeeStatusService searchByEmployeeStatusService;
  private final SearchByJoiningDateService searchByJoiningDateService;
  private final SearchBySalaryService searchBySalaryService;

  public Collection<Employee> search(Search search) {
    log.debug("Search for employees, passed values {}", search);
    if (search.getEmployeeName() != null) {
      return searchByEmployeeNameService.searchEmployees(search);
    } else if (search.getJoiningDateRange() != null) {
      return searchByJoiningDateService.searchEmployees(search);
    } else if (search.getSalaryRange() != null) {
      return searchBySalaryService.searchEmployees(search);
    } else if (search.getEmployeeStatus() != null) {
      return searchByEmployeeStatusService.searchEmployees(search);
    } else if (search.getDepartmentName() != null) {
      return searchByDepartmentNameService.searchEmployees(search);
    }
    return Collections.emptyList();
  }

}
