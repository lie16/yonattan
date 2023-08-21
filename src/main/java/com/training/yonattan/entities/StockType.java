package com.training.yonattan.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class StockType extends  BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stockTypeId;
    @Column(length=3, unique=true, insertable = true, updatable = false)
    private String stockTypeCode;
    private String description;
    private Boolean active;
}
