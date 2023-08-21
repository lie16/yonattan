package com.training.yonattan.repository;

import com.training.yonattan.entities.Stock;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface StockRepo extends
        JpaRepository<Stock, UUID>,
        JpaSpecificationExecutor<Stock> {
    Page<Stock> findAll(Pageable pageable);
}
