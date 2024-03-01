package com.example.demo.repository;

import com.example.demo.modal.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query(value = "SELECT e FROM ProductEntity e")
    List<ProductEntity> findAllProduct();
}
