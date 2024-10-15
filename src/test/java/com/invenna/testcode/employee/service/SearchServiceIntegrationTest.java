package com.invenna.testcode.employee.service;

import com.invenna.testcode.employee.models.Employee;
import com.invenna.testcode.employee.models.EmployeeStatus;
import com.invenna.testcode.employee.models.JoiningDateRange;
import com.invenna.testcode.employee.models.Search;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collection;

public class SearchServiceIntegrationTest {

    protected String basePath = "http://localhost:8081";

    @Test
    public void searchByEmployeeStatus() throws URISyntaxException {

        Search statusSearch = Search.builder()
                .employeeStatus(EmployeeStatus.ACTIVE)
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee/search")
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(statusSearch)
                .post();

        Collection<Employee> responseEmployees = response.getBody().jsonPath().getList("",Employee.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(!responseEmployees.isEmpty());

    }

    @Test
    public void searchByEmployeeNameValidName() throws URISyntaxException {

        Search statusSearch = Search.builder()
                .employeeName("testName")
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee/search")
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(statusSearch)
                .post();

        Collection<Employee> responseEmployees = response.getBody().jsonPath().getList("",Employee.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertFalse(responseEmployees.isEmpty());

    }

    @Test
    public void searchByEmployeeNameInvalidName() throws URISyntaxException {

        Search statusSearch = Search.builder()
                .employeeName("Hacker")
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee/search")
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(statusSearch)
                .post();

        Collection<Employee> responseEmployees = response.getBody().jsonPath().getList("",Employee.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(responseEmployees.isEmpty());

    }


    @Test
    public void searchByDepartmentName() throws URISyntaxException {

        Search statusSearch = Search.builder()
                .departmentName("testDepartmentName")
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee/search")
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(statusSearch)
                .post();

        Collection<Employee> responseEmployees = response.getBody().jsonPath().getList("",Employee.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertFalse(responseEmployees.isEmpty());

    }

    @Test
    public void searchByDepartmentNameInvalidName() throws URISyntaxException {

        Search statusSearch = Search.builder()
                .departmentName("MilitaryZone")
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee/search")
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(statusSearch)
                .post();

        Collection<Employee> responseEmployees = response.getBody().jsonPath().getList("",Employee.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(responseEmployees.isEmpty());

    }

    @Test
    public void searchByJoiningDate() throws URISyntaxException {

        LocalDate fromJoiningDate = LocalDate.parse("2005-11-12");
        LocalDate toJoiningDate = LocalDate.parse("2024-11-12");


        JoiningDateRange joiningDateRange = new JoiningDateRange(fromJoiningDate, toJoiningDate);

        Search statusSearch = Search.builder()
                .joiningDateRange(joiningDateRange)
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee/search")
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(statusSearch)
                .post();

        Collection<Employee> responseEmployees = response.getBody().jsonPath().getList("",Employee.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertFalse(responseEmployees.isEmpty());

    }


    @Test
    public void searchByJoiningDateInvalidRange() throws URISyntaxException {

        LocalDate fromJoiningDate = LocalDate.parse("2000-11-12");
        LocalDate toJoiningDate = LocalDate.parse("2004-11-12");


        JoiningDateRange joiningDateRange = new JoiningDateRange(fromJoiningDate, toJoiningDate);

        Search statusSearch = Search.builder()
                .joiningDateRange(joiningDateRange)
                .build();

        Response response = RestAssured.given().baseUri(basePath + "/employee/search")
                .accept(ContentType.ANY)
                .contentType("application/json;charset=utf-8")
                .body(statusSearch)
                .post();

        Collection<Employee> responseEmployees = response.getBody().jsonPath().getList("",Employee.class);

        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertTrue(responseEmployees.isEmpty());

    }
}
