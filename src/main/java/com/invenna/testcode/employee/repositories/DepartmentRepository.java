package com.invenna.testcode.employee.repositories;

import com.invenna.testcode.employee.entities.DepartmentDbWrapper;
import com.invenna.testcode.employee.entities.EmployeeDbWrapper;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentDbWrapper, Long> {

  Collection<DepartmentDbWrapper> findByNameIgnoreCaseContaining(String name);
}
