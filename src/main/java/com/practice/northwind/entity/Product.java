package com.practice.northwind.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
  @Id
  @Column(name = "product_id")
  private Short productId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "quantity_per_unit")
  private String quantityPerUnit;

  @Column(name = "unit_price")
  private Float unitPrice;

  @Column(name = "units_in_stock")
  private Short unitsInStock;

  @Column(name = "units_on_order")
  private Short unitsOnOrder;

  @Column(name = "reorder_level")
  private Short reorderLevel;

  @Column(name = "discontinued", nullable = false)
  private Integer discontinued;

}
