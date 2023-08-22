package com.training.yonattan.services;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.training.yonattan.entities.StockType;
import com.training.yonattan.handler.request.CreateStockTypeRequest;
import com.training.yonattan.repository.StockTypeRepository;

import jakarta.transaction.Transactional;

@Service
public class StockTypeService {
    @Autowired
    private StockTypeRepository stockTypeRepository;

    @Autowired
    private UsersService usersService;

    public StockType getStockById(int id) {
        Optional<StockType> data = stockTypeRepository.findById(id);
        return data.orElse(null);
    }
    
    public Page<StockType> getAll() {
        Pageable pageable = PageRequest.of(0, 10);
        return stockTypeRepository.findAll(pageable);
    }

    @Transactional
    public String createStockType (CreateStockTypeRequest createStockTypeRequest) throws Exception {
        UUID id = usersService.getCurrentId();
        StockType stockType = new StockType();
        stockType.setStockTypeCode(createStockTypeRequest.getStockTypeCode());
        stockType.setDescription(createStockTypeRequest.getDescription());
        stockType.setActive(createStockTypeRequest.getActive());
        stockType.setCreatedBy(id);
        stockType.setModifiedBy(id);
        stockType.setCreatedDate(new Date());
        stockType.setUpdatedDate(new Date());
        stockTypeRepository.save(stockType);
        return "Stock Type created successfully";
    }
}
