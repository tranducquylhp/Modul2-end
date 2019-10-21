package com.codegym.service.impl;

import com.codegym.model.Product;
import com.codegym.model.ProductType;
import com.codegym.repository.ProductRepository;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Page<Product> findByProductType(ProductType productType, Pageable pageable) {
        return productRepository.findAllByProductTypeContaining(productType, pageable);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Integer> getNumberPage(Page<Product> products) {
        int productPage = products.getTotalPages();
        List<Integer> productsPages = new ArrayList<>();
        for (int i = 0; i < productPage; i++) {
            productsPages.add(i);
        }
        return productsPages;
    }
}
