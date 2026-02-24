package com.practice.northwind.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.practice.northwind.exceptions.ValidationException;
import java.util.Arrays;
import java.util.List;

import com.practice.northwind.dto.Request.ProductPaginationRequest;
import com.practice.northwind.dto.Response.ProductResponse;
import com.practice.northwind.entity.Product;
import com.practice.northwind.repository.ProductRepository;

@Service
public class ProductService {
  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  public ProductResponse mapToResponse(Product product) {
    ProductResponse response = new ProductResponse();
    response.setProductId(product.getProductId());
    response.setProductName(product.getProductName());
    response.setQuantityPerUnit(product.getQuantityPerUnit());
    response.setUnitPrice(product.getUnitPrice());
    response.setUnitsInStock(product.getUnitsInStock());
    response.setUnitsOnOrder(product.getUnitsOnOrder());
    response.setReorderLevel(product.getReorderLevel());
    response.setDiscontinued(product.getDiscontinued());
    return response;
  }

  public Page<ProductResponse> getAllProducts(ProductPaginationRequest request) {
    List<String> validFields = Arrays.asList("productId", "productName", "unitPrice", "unitsInStock", "unitsOnOrder",
        "reorderLevel", "discontinued");
    if (!validFields.contains(request.getSortBy())) {
      throw new ValidationException("Invalid sortBy field: " + request.getSortBy());
    }

    Sort sort = request.getDirection().equalsIgnoreCase("desc")
        ? Sort.by(request.getSortBy()).descending()
        : Sort.by(request.getSortBy()).ascending();
    int size = Math.min(request.getSize(), 50);
    Pageable pageable = PageRequest.of(request.getPage(), size, sort);
    return repository.findAll(pageable).map(this::mapToResponse);
  }
}
