package com.practice.northwind.exceptions;

import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.practice.northwind.dto.ApiErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Handle all custom exceptions
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiErrorResponse> handleApiException(ApiException ex, HttpServletRequest request) {
    ApiErrorResponse error = new ApiErrorResponse(
        ex.getStatus().value(),
        ex.getErrorCode(),
        ex.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(ex.getStatus()).body(error);
  }

  // Handle all validation exceptions
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
      HttpServletRequest request) {
    String message = ex.getBindingResult().getFieldErrors().stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.joining(", "));
    ApiErrorResponse error = new ApiErrorResponse(
        HttpStatus.BAD_REQUEST.value(),
        "VALIDATION_ERROR",
        message,
        request.getRequestURI());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

  }

  // Handle data integrity violation exceptions
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex,
      HttpServletRequest request) {
    String message = "Database integrity violation";
    if (ex.getMostSpecificCause() != null) {
      message = ex.getMostSpecificCause().getMessage();
    }

    ApiErrorResponse error = new ApiErrorResponse(
        HttpStatus.CONFLICT.value(),
        "DATA_INTEGRITY_VIOLATION",
        message,
        request.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  // Handle 404 errors (Resource not found)
  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex,
      HttpServletRequest request) {
    ApiErrorResponse error = new ApiErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        "RESOURCE_NOT_FOUND",
        "The requested resource was not found: " + ex.getResourcePath(),
        request.getRequestURI());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  // Handle all other exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleException(Exception ex, HttpServletRequest request) {
    ApiErrorResponse error = new ApiErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        "INTERNAL_SERVER_ERROR",
        ex.getMessage(),
        request.getRequestURI());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
