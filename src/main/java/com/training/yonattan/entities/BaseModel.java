package com.training.yonattan.entities;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class BaseModel {
    private Date createdDate;
    private Date updatedDate;
    private UUID createdBy;
    private UUID modifiedBy;
}
