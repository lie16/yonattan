package com.training.yonattan.entities;

import java.util.UUID;

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
public class Stock extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID stockId;
    @Column(length=20, unique=true, insertable = true, updatable = false)
    private String stockCode;
    private String description;
    private Boolean active;

//    @OneToOne(fetch = FetchType.LAZY)
//    private Department department;
}
