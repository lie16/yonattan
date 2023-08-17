package com.training.yonattan.repository;

import com.training.yonattan.entities.Employee;
import com.training.yonattan.specification.EmployeeSpecification;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends
        JpaRepository<Employee, Integer>,
        JpaSpecificationExecutor<Employee> {
    Page<Employee> findAll(EmployeeSpecification specification, Pageable pageable);

    List<Employee> findAll(EmployeeSpecification specification);

//    @Query(value = "select e.employee_id as employeeId, e.email as email, e.employee_name as employeeName , " +
//            "d.department_name as department  \n" +
//            "from employee e join department d on e.department_department_id = d.department_id\n" +
//            "where e.employee_id = ?1", nativeQuery = true)
//    public EmployeeDepartment getEmployeeDepartment(int employeeId);

}
