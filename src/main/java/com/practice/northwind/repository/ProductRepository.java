package com.practice.northwind.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.northwind.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Short> {

}
