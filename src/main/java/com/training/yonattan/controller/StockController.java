package com.training.yonattan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.training.yonattan.utils.FunctionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.training.yonattan.entities.Stock;
import com.training.yonattan.handler.request.CreateStockDTO;
import com.training.yonattan.handler.request.FilterStockRequest;
import com.training.yonattan.handler.response.ResponseHandler;
import com.training.yonattan.handler.response.StockResponse;
import com.training.yonattan.services.StocksService;

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
                stockResponse.setStockId(stock.getStockId());
                stockResponse.setStockCode(stock.getStockCode());
                stockResponse.setDescription(stock.getDescription());
                stockResponse.setActive(stock.getActive());
                responses.add(stockResponse);
            }
            return ResponseHandler.generateResponse(FunctionStatus.SUCCESS, HttpStatus.OK,
                    page.getTotalElements(), page.getTotalPages(), responses);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, 0,0,null);
        }
    }

    @GetMapping("/filters")
    public ResponseEntity<Object> getStockFiltered(@ModelAttribute  FilterStockRequest filterParams) {
        try{
            Page<Stock> pageData = stocksService.findAll(
                    filterParams.getPage(),
                    filterParams.getPageSize(),
                    filterParams.getStockCode(),
                    filterParams.getDescription(),
                    filterParams.getActive()
            );
            List<StockResponse> responses = new ArrayList<>();
            for (Stock stock : pageData) {
                StockResponse stockResponse = new StockResponse();
                stockResponse.setStockId(stock.getStockId());
                stockResponse.setStockCode(stock.getStockCode());
                stockResponse.setDescription(stock.getDescription());
                stockResponse.setActive(stock.getActive());
                responses.add(stockResponse);
            }
            return ResponseHandler.generateResponse(FunctionStatus.SUCCESS, HttpStatus.OK,
                    pageData.getTotalElements(), pageData.getTotalPages(), responses);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, 0,0,null);
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Object> getStockById(@RequestParam("stockId") UUID stockId) {
        try{
            Stock responses = stocksService.getStockById(stockId);
            return ResponseHandler.generateResponse(FunctionStatus.SUCCESS, HttpStatus.OK,
                    0, 0, responses);
        } catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, 0,0,null);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createStock(@RequestBody CreateStockDTO createStockDTO) {
        try{
            String response = stocksService.createStock(createStockDTO);
            return ResponseHandler.generateResponse(FunctionStatus.SUCCESS, HttpStatus.OK,
                    0, 0, response);
        } catch (Exception e){
            return ResponseHandler.generateResponse(FunctionStatus.ERROR, HttpStatus.MULTI_STATUS, 0,0,e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateStock(@RequestBody CreateStockDTO createStockDTO) {
        try{
            String response = stocksService.updateStock(createStockDTO);
            return ResponseHandler.generateResponse(FunctionStatus.SUCCESS, HttpStatus.OK,
                    0, 0, response);
        } catch (Exception e){
            return ResponseHandler.generateResponse(FunctionStatus.ERROR, HttpStatus.MULTI_STATUS, 0,0,e.getMessage());
        }
    }
}
