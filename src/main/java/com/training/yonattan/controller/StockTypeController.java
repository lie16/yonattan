package com.training.yonattan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.yonattan.handler.request.CreateStockTypeRequest;
import com.training.yonattan.handler.response.ResponseHandler;
import com.training.yonattan.services.StockTypeService;
import com.training.yonattan.utils.FunctionStatus;

@RestController
@RequestMapping("api-v1/stock-type")
public class StockTypeController {
    @Autowired
    private StockTypeService stockTypeService;
    
    @PostMapping("/create")
    public ResponseEntity<Object> createStock(@RequestBody CreateStockTypeRequest request){
        try{
            String response = stockTypeService.createStockType(request);
            return ResponseHandler.generateResponse(FunctionStatus.SUCCESS, HttpStatus.OK,
                    0, 0, response);
        } catch (Exception e){
            return ResponseHandler.generateResponse(FunctionStatus.ERROR, HttpStatus.MULTI_STATUS, 0,0,e.getMessage());
        }
    }
}
