package com.training.yonattan.repository;

import com.training.yonattan.entities.Employee;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Page<Employee> findAll(Pageable pageable);
}
