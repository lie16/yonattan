package com.training.yonattan.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.training.yonattan.entities.Stock;


// @repository not needed when extending JpaRepository
// @Repository
public interface StockRepo extends
        JpaRepository<Stock, UUID>,
        JpaSpecificationExecutor<Stock> {
    Page<Stock> findAll(Pageable pageable);
}
