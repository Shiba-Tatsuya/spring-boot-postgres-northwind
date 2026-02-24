package com.practice.northwind.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ApiException extends RuntimeException {
  private final HttpStatus status;
  private final String errorCode;

  public ApiException(HttpStatus status, String errorCode, String message) {
    super(message);
    this.status = status;
    this.errorCode = errorCode;
  }
}
