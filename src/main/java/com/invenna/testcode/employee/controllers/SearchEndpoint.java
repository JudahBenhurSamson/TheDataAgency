package com.invenna.testcode.employee.controllers;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.service.EmployeeSearchFactory;
import jakarta.validation.Valid;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/employee")
@RestController
@Validated
public class SearchEndpoint {

  private final EmployeeSearchFactory searchFactory;


  @PostMapping("/search")
  public Collection<Employee> searchEmployees(@Valid @RequestBody Search search) {
    return searchFactory.search(search);
  }

}
