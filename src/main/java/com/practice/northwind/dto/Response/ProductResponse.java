package com.practice.northwind.dto.Response;

import lombok.Data;

@Data
public class ProductResponse {
  private Short productId;
  private String productName;
  private String quantityPerUnit;
  private Float unitPrice;
  private Short unitsInStock;
  private Short unitsOnOrder;
  private Short reorderLevel;
  private Integer discontinued;
}
