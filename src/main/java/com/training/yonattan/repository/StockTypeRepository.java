package com.training.yonattan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.training.yonattan.entities.StockType;

public interface StockTypeRepository extends
        JpaRepository<StockType, Integer>,
        JpaSpecificationExecutor<StockType> {
}
