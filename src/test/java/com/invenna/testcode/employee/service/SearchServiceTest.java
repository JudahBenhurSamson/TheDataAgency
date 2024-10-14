package com.invenna.testcode.employee.service;

// Import necessary classes and packages for the test.
import com.invenna.testcode.employee.entities.EmployeeDbWrapper;
import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.Search;
import com.invenna.testcode.employee.models.EmployeeStatus;
import com.invenna.testcode.employee.models.SalaryRange;
import com.invenna.testcode.employee.models.JoiningDateRange;
import com.invenna.testcode.employee.repositories.DbConvertor;
import com.invenna.testcode.employee.repositories.EmployeeRepository;
import com.invenna.testcode.employee.service.impl.SearchByEmployeeNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

// Extend the JUnit test class with MockitoExtension for mock injection.
@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    // Declare mock dependencies.
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DbConvertor dbConvertor;

    // Inject mock dependencies into the service being tested.
    @InjectMocks
    private SearchByEmployeeNameService searchByEmployeeNameService;

    // Declare test data variables.
    private Employee employee1;
    private Employee employee2;
    private EmployeeDbWrapper employeeDbWrapper1;
    private EmployeeDbWrapper employeeDbWrapper2;
    private Search search;

    // Initialize test data before each test runs.
    @BeforeEach
    void setup() {
        // Build the first employee object.
        employee1 = Employee.builder()
                .id(1L)
                .name("John Doe")
                .build();

        // Build the second employee object.
        employee2 = Employee.builder()
                .id(2L)
                .name("Jane Doe")
                .build();

        // Initialize the first employee database wrapper.
        employeeDbWrapper1 = new EmployeeDbWrapper();
        employeeDbWrapper1.setId(1L);
        employeeDbWrapper1.setName("John Doe");

        // Initialize the second employee database wrapper.
        employeeDbWrapper2 = new EmployeeDbWrapper();
        employeeDbWrapper2.setId(2L);
        employeeDbWrapper2.setName("Jane Doe");

        // Create a search object with criteria for searching employees.
        search = Search.builder()
                .employeeName("Doe")
                .departmentName("IT")
                .employeeStatus(EmployeeStatus.ACTIVE)
                .salaryRange(new SalaryRange(BigDecimal.ZERO, BigDecimal.valueOf(100000)))
                .joiningDateRange(new JoiningDateRange(LocalDate.of(2020, 1, 1), LocalDate.of(2023, 12, 31)))
                .build();
    }

    // Test the search functionality by employee name.
    @Test
    void testSearchEmployeesByName() {
        // Mock the repository call to return predefined employee database wrappers when searched by name.
        when(employeeRepository.findAllByNameIgnoreCaseContaining("Doe"))
                .thenReturn(Arrays.asList(employeeDbWrapper1, employeeDbWrapper2));

        // Mock the database converter to return employee objects from employee database wrappers.
        when(dbConvertor.fromEmployeeDbWrapper(employeeDbWrapper1)).thenReturn(employee1);
        when(dbConvertor.fromEmployeeDbWrapper(employeeDbWrapper2)).thenReturn(employee2);

        // Call the search service to search employees based on the search criteria.
        Collection<Employee> result = searchByEmployeeNameService.searchEmployees(search);

        // Assert that the search result is not null.
        assertNotNull(result);

        // Assert that the search result contains exactly 2 employees.
        assertEquals(2, result.size());

        // Assert that the search result matches the predefined employees.
        assertEquals(Arrays.asList(employee1, employee2), result);
    }

    // Test the search functionality when no results are found.
    @Test
    void testSearchEmployeesNoResults() {
        // Mock the repository call to return an empty list when searched by name.
        when(employeeRepository.findAllByNameIgnoreCaseContaining("Doe")).thenReturn(List.of());

        // Call the search service to search employees based on the search criteria.
        Collection<Employee> result = searchByEmployeeNameService.searchEmployees(search);

        // Assert that the search result is not null.
        assertNotNull(result);

        // Assert that the search result contains zero employees.
        assertEquals(0, result.size());
    }
}
