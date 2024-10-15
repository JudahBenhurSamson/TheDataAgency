package com.invenna.testcode.employee.service;

import com.invenna.testcode.employee.models.Department;
import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.EmployeeStatus;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URISyntaxException;


public class EmployeeServiceIntegrationTest {

    protected String basePath = "http://localhost:8081";

    @Test
    public void postEmployer() throws URISyntaxException {
        long id = 0;
        String name = "testName";
        long departmentId = 0;
        String departmentName = "testDepartmentName";
        String salary = "50000";
        String employeeStatus = "ACTIVE";
        String joiningDate = "01-01-2024";


        Employee req = Employee.builder().id(id).name(name)
                .department(Department.builder().id(departmentId).name(departmentName).build())
                .salary(new BigDecimal("50000")).employeeStatus(EmployeeStatus.ACTIVE)
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee")
                .config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("*/*", ContentType.JSON)))
                .header(new Header("Content-Type", "application/json;charset=utf-8"))
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(req)
                .post();

        Employee responseEmployee = response.getBody().jsonPath().getObject("", Employee.class);
        Assertions.assertEquals(201, response.getStatusCode());
        Assertions.assertEquals(name, responseEmployee.getName());
        Assertions.assertEquals(departmentName, responseEmployee.getDepartment().getName());
        Assertions.assertEquals(salary, responseEmployee.getSalary().toString());
        Assertions.assertEquals(employeeStatus, responseEmployee.getEmployeeStatus().toString());
    }

    @Test
    public void putEmployer() throws URISyntaxException {
        postEmployer();
        long id = 2;
        String name = "testName";
        long departmentId = 1002;
        String departmentName = "testDepartmentName";
        String salary = "60000";
        String employeeStatus = "ACTIVE";
        String joiningDate = "01-02-2024";

        Employee req = Employee.builder().id(id).name(name)
                .department(Department.builder().id(departmentId).name(departmentName).build())
                .salary(new BigDecimal(salary)).employeeStatus(EmployeeStatus.ACTIVE)
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee/" + id)
                .header(new Header("Content-Type", "application/json;charset=utf-8"))
                .accept(ContentType.ANY)
                .body(req)
                .put();

        Employee responseEmployeeTest = response.getBody().jsonPath().getObject("", Employee.class);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(id, responseEmployeeTest.getId());
        Assertions.assertEquals(name, responseEmployeeTest.getName());
        Assertions.assertEquals(salary, responseEmployeeTest.getSalary().toString());
        Assertions.assertEquals(employeeStatus, responseEmployeeTest.getEmployeeStatus().toString());
    }

    @Test
    public void getEmployer() throws URISyntaxException {
        long id = 1;
        String name = "testName";
        long departmentId = 1002;
        String departmentName = "testDepartmentName";
        String salary = "50000.0";
        String employeeStatus = "ACTIVE";
        String joiningDate = "01-01-2024";

        Response response = RestAssured.given().baseUri(basePath + "/employee/" + id)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .get();

        Employee responseEmployee = response.getBody().jsonPath().getObject("", Employee.class);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(id, responseEmployee.getId());
        Assertions.assertEquals(name, responseEmployee.getName());

        Assertions.assertEquals(salary, responseEmployee.getSalary().toString());
        Assertions.assertEquals(employeeStatus, responseEmployee.getEmployeeStatus().toString());
    }

    @Test
    public void deleteEmployer() throws URISyntaxException {
        long id = 1;

        Response response = RestAssured.given().baseUri(basePath + "/employee/" + id)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .delete();

        Assertions.assertEquals(204, response.getStatusCode());
    }

    @Test
    public void searchEmployer() throws URISyntaxException {
        //TODO
    }
}

