package com.training.yonattan.handler.request;

import java.util.Optional;

import lombok.Data;

@Data
public class CreateStockTypeRequest {
    private Optional<Integer> stockTypeId;
    private String stockTypeCode;
    private String description;
    private Boolean active;
}
