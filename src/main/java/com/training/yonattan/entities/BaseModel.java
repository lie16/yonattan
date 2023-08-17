package com.training.yonattan.entities;

import lombok.Data;

import java.util.Date;

@Data
public class BaseModel {
    private Date createdDate;
    private Date updatedDate;
    private String createdBy;
    private String modifiedBy;
}
