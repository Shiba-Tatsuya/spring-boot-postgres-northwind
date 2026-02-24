package com.practice.northwind.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.northwind.dto.ApiResponse;
import com.practice.northwind.dto.Request.ProductPaginationRequest;
import com.practice.northwind.dto.Response.ProductResponse;
import com.practice.northwind.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(
      @jakarta.validation.Valid ProductPaginationRequest request) {

    Page<ProductResponse> productsPage = productService.getAllProducts(request);
    ApiResponse<List<ProductResponse>> response = new ApiResponse<>();
    response.setData(productsPage.getContent());
    response.setMessage("Products fetched successfully");
    response.setSuccess(true);
    response.setHasMore(productsPage.hasNext());
    response.setTotalItems(productsPage.getTotalElements());
    response.setTotalPages(productsPage.getTotalPages());
    response.setCurrentPage(productsPage.getNumber());
    response.setPageSize(productsPage.getSize());
    return ResponseEntity.ok(response);
  }
}
