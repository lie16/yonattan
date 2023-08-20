package com.training.yonattan.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public class BaseModel {
    private Date createdDate;
    private Date updatedDate;
    private UUID createdBy;
    private UUID modifiedBy;
}
