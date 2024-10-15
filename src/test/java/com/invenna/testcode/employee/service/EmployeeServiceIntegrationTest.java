package com.invenna.testcode.employee.service;

// Import necessary classes and dependencies for testing

import com.invenna.testcode.employee.models.Department;
import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.EmployeeStatus;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;


public class EmployeeServiceIntegrationTest {

    // Define the base path for the API
    protected String basePath = "http://localhost:8081";

    // Test method for creating a new employee
    @Test
    public void postEmployer() throws URISyntaxException {
        // Initialize test data for the new employee
        long id = 0;
        String name = "testName";
        long departmentId = 0;
        String departmentName = "testDepartmentName";
        String salary = "50000";
        String employeeStatus = "ACTIVE";
        LocalDate joiningDate = LocalDate.parse("2005-11-12");

        // Create an Employee object using the builder pattern
        Employee req = Employee.builder().id(id).name(name)
                .department(Department.builder().id(departmentId).name(departmentName).build())
                .salary(new BigDecimal(salary)).employeeStatus(EmployeeStatus.ACTIVE)
                .joiningDate(joiningDate)
                .build();

        // Send a POST request to create the employee
        Response response = RestAssured.given().baseUri(basePath + "/employee")
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(req)
                .post();

        // Parse the response into an Employee object
        Employee responseEmployee = response.getBody().jsonPath().getObject("", Employee.class);

        // Assert that the response status code is 201 (Created)
        Assertions.assertEquals(201, response.getStatusCode());

        // Assert that the response data matches the request data
        Assertions.assertEquals(name, responseEmployee.getName());
        Assertions.assertEquals(departmentName, responseEmployee.getDepartment().getName());
        Assertions.assertEquals(salary, responseEmployee.getSalary().toString());
        Assertions.assertEquals(employeeStatus, responseEmployee.getEmployeeStatus().toString());
    }

    // Test method for updating an existing employee
    @Test
    public void putEmployer() throws URISyntaxException {
        // Ensure the employee exists by posting it first
        postEmployer();

        // Initialize updated test data for the employee
        long id = 2;  // Update the ID based on the employee to update
        String name = "testName";
        long departmentId = 1002;
        String departmentName = "testDepartmentName";
        String salary = "60000";
        String employeeStatus = "ACTIVE";
        LocalDate joiningDate = LocalDate.parse("2005-11-12");
        // Create an Employee object with updated details
        Employee req = Employee.builder().id(id).name(name)
                .department(Department.builder().id(departmentId).name(departmentName).build())
                .salary(new BigDecimal(salary)).employeeStatus(EmployeeStatus.ACTIVE)
                .joiningDate(joiningDate)
                .build();

        // Send a PUT request to update the employee
        Response response = RestAssured.given().baseUri(basePath + "/employee/" + id)
                .header(new Header("Content-Type", "application/json;charset=utf-8"))
                .accept(ContentType.ANY)
                .body(req)
                .put();

        // Parse the response into an Employee object
        Employee responseEmployeeTest = response.getBody().jsonPath().getObject("", Employee.class);

        // Assert that the response status code is 200 (OK)
        Assertions.assertEquals(200, response.getStatusCode());

        // Assert that the response data matches the updated request data
        Assertions.assertEquals(id, responseEmployeeTest.getId());
        Assertions.assertEquals(name, responseEmployeeTest.getName());
        Assertions.assertEquals(departmentName, responseEmployeeTest.getDepartment().getName());
        Assertions.assertEquals(salary, responseEmployeeTest.getSalary().toString());
        Assertions.assertEquals(employeeStatus, responseEmployeeTest.getEmployeeStatus().toString());
    }

    // Test method for retrieving an existing employee
    @Test
    public void getEmployer() throws URISyntaxException {
        // Initialize test data for the existing employee
        long id = 1;  // Use the ID of the employee to retrieve
        String name = "testName";
        long departmentId = 1;
        String departmentName = "testDepartmentName";
        String salary = "50000.0";
        String employeeStatus = "ACTIVE";
        String joiningDate = "01-01-2024";

        // Send a GET request to retrieve the employee details
        Response response = RestAssured.given().baseUri(basePath + "/employee/" + id)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get();

        // Parse the response into an Employee object
        Employee responseEmployee = response.getBody().jsonPath().getObject("", Employee.class);

        // Assert that the response status code is 200 (OK)
        Assertions.assertEquals(200, response.getStatusCode());

        // Assert that the response data matches the expected employee details
        Assertions.assertEquals(id, responseEmployee.getId());
        Assertions.assertEquals(name, responseEmployee.getName());
        Assertions.assertEquals(departmentName, responseEmployee.getDepartment().getName());
        Assertions.assertEquals(departmentId, responseEmployee.getDepartment().getId());
        Assertions.assertEquals(salary, responseEmployee.getSalary().toString());
        Assertions.assertEquals(employeeStatus, responseEmployee.getEmployeeStatus().toString());
    }

    // Test method for deleting an existing employee
    @Test
    public void deleteEmployer() throws URISyntaxException {
        // Initialize test data for the employee to delete
        long id = 1;  // Use the ID of the employee to delete

        // Send a DELETE request to remove the employee
        Response response = RestAssured.given().baseUri(basePath + "/employee/" + id)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .delete();

        // Assert that the response status code is 204 (No Content)
        Assertions.assertEquals(204, response.getStatusCode());
    }
}
