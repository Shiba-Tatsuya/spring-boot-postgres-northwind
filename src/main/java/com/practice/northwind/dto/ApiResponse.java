package com.practice.northwind.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
  private T data;
  private String message;
  private boolean success;
  private Boolean hasMore;
  private Long totalItems;
  private Integer totalPages;
  private Integer currentPage;
  private Integer pageSize;

  public ApiResponse(T data, String message, boolean success) {
    this.data = data;
    this.message = message;
    this.success = success;
    this.hasMore = null;
    this.totalItems = null;
    this.totalPages = null;
    this.currentPage = null;
    this.pageSize = null;
  }
}
