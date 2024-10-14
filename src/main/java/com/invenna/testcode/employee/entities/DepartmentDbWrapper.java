package com.invenna.testcode.employee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Department", indexes = {
    @Index(name = "idx_department", columnList = "name")})
public class DepartmentDbWrapper {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String name;
}
