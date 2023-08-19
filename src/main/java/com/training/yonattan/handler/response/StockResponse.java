package com.training.yonattan.handler.response;

import lombok.Data;

//These method support @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
//Full set table
@Data
public class StockResponse {
  private String stockCode;
  private String description;
  private boolean active;
//  private long totalData;
//  private int totalPages;
}
