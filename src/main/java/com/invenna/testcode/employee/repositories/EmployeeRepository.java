package com.invenna.testcode.employee.repositories;

import com.invenna.testcode.employee.entities.EmployeeDbWrapper;
import com.invenna.testcode.employee.models.EmployeeStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDbWrapper, Long> {

  Collection<EmployeeDbWrapper> findAllByNameIgnoreCaseContaining(String name);

  Collection<EmployeeDbWrapper> findAllByDepartmentId(long id);


  Collection<EmployeeDbWrapper> findAllBySalaryBetween(BigDecimal startRange, BigDecimal endRange);

  Collection<EmployeeDbWrapper> findAllByJoiningDateBetween(LocalDate startDate, LocalDate endDate);

  Collection<EmployeeDbWrapper> findAllByEmployeeStatus(EmployeeStatus employeeStatus);
}
