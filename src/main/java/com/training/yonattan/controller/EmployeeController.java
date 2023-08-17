package com.training.yonattan.controller;

import com.training.yonattan.entities.Employee;
import com.training.yonattan.handler.response.EmployeesResponse;
import com.training.yonattan.handler.response.ResponseHandler;
import com.training.yonattan.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api-v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity getEmployees() {
        try{
            Page<Employee> page = employeeService.getAll();
            List<EmployeesResponse> responses = new ArrayList<>();
            for (Employee employee : page) {
                EmployeesResponse employeesResponse = new EmployeesResponse();
                employeesResponse.setNik(employee.getNik());
                employeesResponse.setEmployeeName(employee.getEmployeeName());
                employeesResponse.setAddress(employee.getAddress());
                employeesResponse.setCity(employee.getCity());
                employeesResponse.setProvince(employee.getProvince());
                employeesResponse.setEmail(employee.getEmail());
                employeesResponse.setPhoneNumber(employee.getPhoneNumber());
                employeesResponse.setActive(employee.getActive());
                responses.add(employeesResponse);
            }
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK,
                    page.getTotalElements(), page.getTotalPages(), responses);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, 0,0,null);
        }
    }
}
