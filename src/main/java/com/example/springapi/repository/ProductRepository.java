package com.example.springapi.repository;

import com.example.springapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByTitleContaining(String keyword);
}
