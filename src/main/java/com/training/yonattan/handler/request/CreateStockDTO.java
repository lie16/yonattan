package com.training.yonattan.handler.request;

import lombok.Data;

import java.util.Optional;
import java.util.UUID;

@Data
public class CreateStockDTO {
    private Optional<UUID> stockId;
    private String stockCode;
    private String description;
    private Boolean active;
    private Integer stockTypeId;
}
