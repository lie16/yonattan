package com.training.yonattan.services;

import com.training.yonattan.entities.Employee;
import com.training.yonattan.handler.response.EmployeesResponse;
import com.training.yonattan.repository.EmployeeRepo;
import com.training.yonattan.specification.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    private EmployeeSpecification employeeSpecification;

    public Employee getEmployeeById(int id)
    {
        Optional<Employee> employee = employeeRepo.findById(id);
//        return employee.isPresent() ? employee.get() : null;
        return employee.orElse(null);
    }

    public Page<Employee> getAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> page = employeeRepo.findAll(pageable);
        return page;
    }
    public Page<Employee> findAll(int page, int pageSize, String nik,
                                  String employeeName,
                                  String address,
                                  String city,
                                  String province,
                                  String email,
                                  String phoneNumber,
                                  String active)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Employee> employees = employeeRepo.findAll(employeeSpecification.filter(
                nik,
                employeeName,
                address,
                city,
                province,
                email,
                phoneNumber,
                active
        ), pageable);
        System.out.println(employees);

//        return employee.isPresent() ? employee.get() : null;
        return employees;
    }
}
