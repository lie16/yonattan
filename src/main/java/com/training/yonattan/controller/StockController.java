package com.training.yonattan.controller;

import com.training.yonattan.entities.Stock;
import com.training.yonattan.handler.request.CreateStockDTO;
import com.training.yonattan.handler.response.StockResponse;
import com.training.yonattan.handler.response.ResponseHandler;
import com.training.yonattan.services.StocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api-v1/stock")
public class StockController {
    @Autowired
    private StocksService stocksService;

    @GetMapping("")
    public ResponseEntity<Object> getStock() {
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

    @GetMapping("/find-by-id")
    public ResponseEntity<Object> getStockById() {
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

    @PostMapping("/create")
    public ResponseEntity<Object> createEmployees(@RequestBody CreateStockDTO createStockDTO) {
        try{
            String response = stocksService.createStock(createStockDTO);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.OK,
                    0, 0, response);
        } catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, 0,0,e.getMessage());
        }
    }
}
