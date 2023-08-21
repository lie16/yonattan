package com.training.yonattan.handler.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class FilterStockRequest extends BaseRequest{
    private String stockCode;
    private String description;
    private String active;
    private Integer stockTypeId;
}
