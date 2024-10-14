package com.invenna.testcode.employee.models;

import static com.invenna.testcode.employee.constants.EmployeeConstants.FREE_TEXT_REGEX;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Search {

  @Pattern(message = "Employee Name contains invalid characters", regexp = FREE_TEXT_REGEX)
  String employeeName;
  @Pattern(message = "Department Name contains invalid characters", regexp = FREE_TEXT_REGEX)
  String departmentName;

  EmployeeStatus employeeStatus;

  SalaryRange salaryRange;

  JoiningDateRange joiningDateRange;

}
