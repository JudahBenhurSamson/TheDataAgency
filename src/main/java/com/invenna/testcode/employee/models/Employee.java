package com.invenna.testcode.employee.models;

import static com.invenna.testcode.employee.constants.EmployeeConstants.FREE_TEXT_REGEX;

import jakarta.persistence.Index;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor  // No-arg constructor
@AllArgsConstructor // All-arg constructor
public class Employee {

  long id;
  @NotNull
  @Pattern(message = "Name contains invalid characters", regexp = FREE_TEXT_REGEX)
  String name;
  @Valid
  Department department;
  BigDecimal salary;
  EmployeeStatus employeeStatus;
  LocalDate joiningDate;

}
