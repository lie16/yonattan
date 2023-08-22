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
import com.training.yonattan.entities.StockType;
import com.training.yonattan.entities.Users;
import com.training.yonattan.handler.request.CreateStockDTO;
import com.training.yonattan.handler.request.FilterStockRequest;
import com.training.yonattan.repository.StockRepo;
import com.training.yonattan.repository.StockTypeRepository;
import com.training.yonattan.specification.StocksSpecification;

@Service
public class StocksService {
    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private StockTypeRepository stockTypeRepository;

    @Autowired
    private StocksSpecification stocksSpecification;

    public Stock getStockById(UUID id) {
        Optional<Stock> stock = stockRepo.findById(id);
        return stock.orElse(null);
    }

    public Page<Stock> getAll() {
        Pageable pageable = PageRequest.of(0, 10);
        return stockRepo.findAll(pageable);
    }

    public Page<Stock> findAll(FilterStockRequest filterParams) {
        Pageable pageable = PageRequest.of(filterParams.getPage(), filterParams.getPageSize());
        System.out.println("service");
        System.out.println(filterParams.getStockCode());
        System.out.println(filterParams.getPageSize());
        System.out.println(filterParams.getStockTypeId());
        return stockRepo.findAll(stocksSpecification.filter(filterParams.getStockCode(), filterParams.getDescription(),
                filterParams.getActive(), filterParams.getStockTypeId()), pageable);
    }

    @Transactional
    public String createStock(CreateStockDTO createStockDTO) throws Exception {
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

        Optional<StockType> stockType = stockTypeRepository.findById(createStockDTO.getStockTypeId());

        StockType stockTypeData = stockType.orElse(null);
        if (stockTypeData == null) {
            throw new Exception("Stock type " + createStockDTO.getStockTypeId() + " not found");
        }
        if (Boolean.FALSE.equals(stockTypeData.getActive())) {
            throw new Exception("Stock type no longer active.");
        }

        stock.setStockType(stockTypeData);
        stock.setCreatedDate(new Date());
        stock.setUpdatedDate(new Date());
        stock.setCreatedBy(id);
        stock.setModifiedBy(id);
        stockRepo.save(stock);
        return "Stock created successfully";
    }

    @Transactional
    public String updateStock(CreateStockDTO createStockDTO) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        UUID userId = null;
        if (principal instanceof Users users) {
            userId = users.getUserId();
        } else {
            userId = UUID.fromString(principal.toString());
        }
        UUID stockId = createStockDTO.getStockId().orElse(null);
        if (stockId == null) {
            throw new Exception("Stock code " + createStockDTO.getStockCode() + " must be set");
        }
        Optional<Stock> optStock = stockRepo.findById(stockId);
        Stock data = optStock.orElse(null);

        if (data == null) {
            throw new Exception("Stock code " + createStockDTO.getStockCode() + " not found");
        }
        data.setDescription(createStockDTO.getDescription());
        Optional<StockType> stockType = stockTypeRepository.findById(createStockDTO.getStockTypeId());

        StockType stockTypeData = stockType.orElse(null);
        if (stockTypeData == null) {
            throw new Exception("Stock type " + createStockDTO.getStockTypeId() + " not found");
        }
        data.setStockType(stockTypeData);
        data.setActive(createStockDTO.getActive());
        data.setModifiedBy(userId);
        stockRepo.save(data);
        return "Stock updated successfully";
    }
}
