package com.training.yonattan.controller;

import com.training.yonattan.entities.Stock;
import com.training.yonattan.handler.response.StockResponse;
import com.training.yonattan.handler.response.ResponseHandler;
import com.training.yonattan.services.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api-v1/employee")
public class StockController {
    @Autowired
    private StocksService stocksService;

    @GetMapping("")
    public ResponseEntity getEmployees() {
        try{
            Page<Stock> page = stocksService.getAll();
            List<StockResponse> responses = new ArrayList<>();
            for (Stock stock : page) {
                StockResponse stockResponse = new StockResponse();
                stockResponse.setStockCode(stock.getStockCode());
                stockResponse.setDescription(stock.getDescription());
                stockResponse.setActive(stock.getActive());
                responses.add(stockResponse);
            }
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK,
                    page.getTotalElements(), page.getTotalPages(), responses);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, 0,0,null);
        }
    }
}
