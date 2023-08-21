package com.training.yonattan.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class StockType extends  BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stockTypeId;
    @Column(length=5, unique=true, insertable = true, updatable = false)
    private String stockTypeCode;
    private String description;
    private Boolean active;
}
