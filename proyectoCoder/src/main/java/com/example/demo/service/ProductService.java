package com.example.demo.service;

import com.example.demo.modal.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductEntity create(ProductEntity product);

    Optional<ProductEntity> findByIdP(long id);

    List<ProductEntity> products();

    void deleteProduct(Long id);

    Optional<ProductEntity> modiftStock(Long id, int price);

}
