package com.training.yonattan.handler.request;

import lombok.Data;

@Data
public class FilterStockRequest extends BaseRequest{
    private String stockCode;
    private String description;
    private String active;
}
