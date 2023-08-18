package com.training.yonattan.services;

import com.training.yonattan.entities.Stock;
import com.training.yonattan.handler.request.CreateStockDTO;
import com.training.yonattan.repository.StockRepo;
import com.training.yonattan.specification.StocksSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class StocksService {
    @Autowired
    private StockRepo stockRepo;

    private StocksSpecification stocksSpecification;

    public Stock getEmployeeById(int id)
    {
        Optional<Stock> employee = stockRepo.findById(id);
//        return employee.isPresent() ? employee.get() : null;
        return employee.orElse(null);
    }

    public Page<Stock> getAll(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Stock> page = stockRepo.findAll(pageable);
        return page;
    }
    public Page<Stock> findAll(int page, int pageSize, String stockCode,
                               String description,
                               String active)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Stock> employees = stockRepo.findAll(stocksSpecification.filter(stockCode,
                description,
                active), pageable);
        System.out.println(employees);

//        return employee.isPresent() ? employee.get() : null;
        return employees;
    }

    @Transactional
    public String createStock(CreateStockDTO createStockDTO) {
        Stock stock = new Stock();
        stock.setStockCode(createStockDTO.getStockCode());
        stock.setDescription(createStockDTO.getDescription());
        stock.setActive(createStockDTO.getActive());
        stock.setCreatedDate(new Date());
        stock.setUpdatedDate(new Date());
        stockRepo.save(stock);
        return "employee created successfully";
    }
}
