package com.training.yonattan.services;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.yonattan.entities.Stock;
import com.training.yonattan.entities.Users;
import com.training.yonattan.handler.request.CreateStockDTO;
import com.training.yonattan.repository.StockRepo;
import com.training.yonattan.specification.StocksSpecification;

@Service
public class StocksService {
    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private StocksSpecification stocksSpecification;

    public Stock getStockById(int id) {
        Optional<Stock> employee = stockRepo.findById(id);
        return employee.orElse(null);
    }

    public Page<Stock> getAll() {
        Pageable pageable = PageRequest.of(0, 10);
        return stockRepo.findAll(pageable);
    }

    public Page<Stock> findAll(int page, int pageSize, String stockCode,
            String description,
            String active) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return stockRepo.findAll(stocksSpecification.filter(stockCode, description, active), pageable);
    }

    @Transactional
    public String createStock(CreateStockDTO createStockDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        UUID id = null;
        if (principal instanceof Users users) {
            id = users.getUserId();
        } else {
            id = UUID.fromString(principal.toString());
        }
        Stock stock = new Stock();
        stock.setStockCode(createStockDTO.getStockCode());
        stock.setDescription(createStockDTO.getDescription());
        stock.setActive(createStockDTO.getActive());
        stock.setCreatedDate(new Date());
        stock.setUpdatedDate(new Date());
        stock.setCreatedBy(id);
        stock.setModifiedBy(id);
        stockRepo.save(stock);
        return "employee created successfully";
    }

    @Transactional
    public String updateStock(CreateStockDTO createStockDTO, UUID id) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        UUID userId = null;
        if (principal instanceof Users users) {
            userId = users.getUserId();
        } else {
            userId = UUID.fromString(principal.toString());
        }

//        TODO perlu dipindah ke spec juga
        Optional<Stock> data = stockRepo.findById(id);

        if(data.isEmpty()) {
            throw new Exception("Stock code " + createStockDTO.getStockCode() + " not found");
        }
        data.get().setDescription(createStockDTO.getDescription());
        data.get().setActive(createStockDTO.getActive());
        data.get().setModifiedBy(userId);
    }
}
