package com.training.yonattan.handler.request;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseRequest {
    private int page;
    private int pageSize;
}
