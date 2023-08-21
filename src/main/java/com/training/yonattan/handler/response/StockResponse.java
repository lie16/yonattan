package com.training.yonattan.handler.response;

import java.util.UUID;

import lombok.Data;

//These method support @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
//Full set table
@Data
public class StockResponse {
  private UUID stockId;
  private String stockCode;
  private String description;
  private boolean active;
  private String stockTypeCode;
  private String stockTypeDescription;
//  private long totalData;
//  private int totalPages;
}
