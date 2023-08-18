package com.training.yonattan.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
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
