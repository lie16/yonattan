package com.training.yonattan.repository;

import com.training.yonattan.entities.Stock;
import com.training.yonattan.specification.StocksSpecification;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepo extends
        JpaRepository<Stock, Integer>,
        JpaSpecificationExecutor<Stock> {
    Page<Stock> findAll(StocksSpecification specification, Pageable pageable);

    List<Stock> findAll(StocksSpecification specification);

//    @Query(value = "select e.employee_id as employeeId, e.email as email, e.employee_name as employeeName , " +
//            "d.department_name as department  \n" +
//            "from employee e join department d on e.department_department_id = d.department_id\n" +
//            "where e.employee_id = ?1", nativeQuery = true)
//    public EmployeeDepartment getEmployeeDepartment(int employeeId);

}
