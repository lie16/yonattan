package com.training.yonattan.handler.request;

import lombok.Data;

@Data
public class SignInRequest {
  private String email;
  private String password;
}
