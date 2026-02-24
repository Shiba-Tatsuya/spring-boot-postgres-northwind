package com.practice.northwind.dto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPaginationRequest {

  @Min(value = 0, message = "Page index must not be less than zero")
  private int page = 0;

  @Min(value = 1, message = "Page size must not be less than one")
  private int size = 10;

  private String sortBy = "productName";

  @Pattern(regexp = "asc|desc", message = "Direction must be 'asc' or 'desc'")
  private String direction = "asc";
}
