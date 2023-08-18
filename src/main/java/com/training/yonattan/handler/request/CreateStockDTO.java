package com.training.yonattan.handler.request;

import lombok.Data;

@Data
public class CreateStockDTO {
    private String stockCode;
    private String description;
    private Boolean active;
}
