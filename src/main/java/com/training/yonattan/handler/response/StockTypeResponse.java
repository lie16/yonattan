package com.training.yonattan.handler.response;

import lombok.Data;

@Data
public class StockTypeResponse {
    private String stockTypeCode;
    private String description;
    private Boolean active;
}
