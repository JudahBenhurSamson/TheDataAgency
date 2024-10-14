package com.invenna.testcode.employee.service;

// Import statements for various dependencies required in the test class.
import com.invenna.testcode.employee.entities.DepartmentDbWrapper;
import com.invenna.testcode.employee.entities.EmployeeDbWrapper;
import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.EmployeeStatus;
import com.invenna.testcode.employee.repositories.DbConvertor;
import com.invenna.testcode.employee.repositories.DepartmentRepository;
import com.invenna.testcode.employee.repositories.EmployeeRepository;
import com.invenna.testcode.employee.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.invenna.testcode.employee.models.Department;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Extends JUnit 5 with Mockito functionality.
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    // Mocking the repositories and converters used in the service.
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DbConvertor dbConvertor;

    // Injecting the mocks into the EmployeeService implementation.
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    // Defining the variables used in the tests.
    private Employee employee;
    private EmployeeDbWrapper employeeDbWrapper;
    private DepartmentDbWrapper departmentDbWrapper;

    // Setting up the test data before each test.
    @BeforeEach
    void setup() {
        System.out.println("Started Testing");
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartment(department);
        employee.setSalary(BigDecimal.valueOf(100000.0));
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);
        employee.setJoiningDate(LocalDate.parse("2020-01-01"));

        departmentDbWrapper = new DepartmentDbWrapper();
        departmentDbWrapper.setId(1L);
        departmentDbWrapper.setName("IT");

        employeeDbWrapper = new EmployeeDbWrapper();
        employeeDbWrapper.setId(1L);
        employeeDbWrapper.setName("John Doe");
        employeeDbWrapper.setDepartment(departmentDbWrapper);
        employeeDbWrapper.setSalary(BigDecimal.valueOf(100000.0));
        employeeDbWrapper.setEmployeeStatus(EmployeeStatus.ACTIVE);
        employeeDbWrapper.setJoiningDate(LocalDate.parse("2020-01-01"));
    }

    // Test case for retrieving an employee by ID.
    @Test
    void testRetrieveEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employeeDbWrapper));
        when(dbConvertor.fromEmployeeDbWrapper(employeeDbWrapper)).thenReturn(employee);

        Employee retrievedEmployee = employeeService.retrieveEmployeeById(1L);

        assertNotNull(retrievedEmployee);
        assertEquals(employee, retrievedEmployee);

        verify(employeeRepository).findById(1L);
        verify(dbConvertor).fromEmployeeDbWrapper(employeeDbWrapper);
    }

    // Test case for creating a new employee.
    @Test
    void testCreateEmployee() {
        when(dbConvertor.toEmployeeDbWrapper(employee)).thenReturn(employeeDbWrapper);
        when(employeeRepository.save(employeeDbWrapper)).thenReturn(employeeDbWrapper);
        when(dbConvertor.fromEmployeeDbWrapper(employeeDbWrapper)).thenReturn(employee);

        Employee createdEmployee = employeeService.create(employee);

        assertNotNull(createdEmployee);
        assertEquals(employee, createdEmployee);

        verify(employeeRepository).save(employeeDbWrapper);
        verify(dbConvertor).toEmployeeDbWrapper(employee);
        verify(dbConvertor).fromEmployeeDbWrapper(employeeDbWrapper);
    }

    // Test case for updating an employee's details.
    @Test
    void testUpdateEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employeeDbWrapper));
        when(dbConvertor.fromEmployeeDbWrapper(employeeDbWrapper)).thenReturn(employee);
        when(employeeRepository.save(employeeDbWrapper)).thenReturn(employeeDbWrapper);
        when(dbConvertor.fromEmployeeDbWrapper(employeeDbWrapper)).thenReturn(employee);

        Employee updatedEmployee = employeeService.update(1L, employee);

        assertNotNull(updatedEmployee);
        assertEquals(employee, updatedEmployee);

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(employeeDbWrapper);
        verify(dbConvertor).fromEmployeeDbWrapper(employeeDbWrapper);
        verify(dbConvertor).fromEmployeeDbWrapper(employeeDbWrapper);
    }

    // Test case for deleting an employee.
    @Test
    void testDeleteEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employeeDbWrapper));

        employeeService.delete(1L);

        verify(employeeRepository).delete(employeeDbWrapper);
    }
}
