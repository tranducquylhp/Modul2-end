package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    void save(Product product);
    void delete(Long id);
    Page<Product> findByProductType(ProductType productType, Pageable pageable);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    Product findById(Long id);
    List<Integer> getNumberPage(Page<Product> products);
}
