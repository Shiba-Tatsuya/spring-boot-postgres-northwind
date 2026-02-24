package com.practice.northwind.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
  private final int status;
  private final String error;
  private final String message;
  private final String path;
  private final LocalDateTime timestamp;

  public ApiErrorResponse(int status, String error, String message, String path) {
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
    this.timestamp = LocalDateTime.now();
  }
}
