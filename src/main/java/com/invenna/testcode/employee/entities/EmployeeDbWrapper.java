package com.invenna.testcode.employee.entities;

import com.invenna.testcode.employee.models.EmployeeStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Data
@Table(name = "Employee", indexes = {
        @Index(name = "idx_employee", columnList = "name,salary,employeeStatus,joiningDate")})
public class EmployeeDbWrapper {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String name;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "department_id")
  DepartmentDbWrapper department;

  BigDecimal salary;
  EmployeeStatus employeeStatus;
  LocalDate joiningDate;
}
