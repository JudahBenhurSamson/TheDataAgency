package com.invenna.testcode.employee.models;


import lombok.Getter;

@Getter
public enum EmployeeStatus {

  ACTIVE("active"),
  ON_LEAVE("on leave"),
  TERMINATED("terminated");

  final String status;

  EmployeeStatus(String status) {
    this.status = status;
  }

  public static EmployeeStatus fromValue(String value) {
    for (EmployeeStatus employeeStatus : EmployeeStatus.values()) {
      if (employeeStatus.name().equalsIgnoreCase(value)) {
        return employeeStatus;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
