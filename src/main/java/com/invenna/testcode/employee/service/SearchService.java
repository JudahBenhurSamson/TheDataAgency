package com.invenna.testcode.employee.service;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.Search;
import java.util.Collection;

public interface SearchService {

  Collection<Employee> searchEmployees(Search search);
}
