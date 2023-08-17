package com.training.yonattan.handler.response;

import lombok.Data;

//These method support @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
//Full set table
@Data
public class EmployeesResponse {
  private String nik;
  private String employeeName;
  private String address;
  private String city;
  private String province;
  private String email;
  private String phoneNumber;
  private boolean active;
//  private long totalData;
//  private int totalPages;
}
