package com.training.yonattan.entities;

import java.util.UUID;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private StockType stockType;
}
