package com.example.demo.service.impl;

import com.example.demo.modal.ProductEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity create(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<ProductEntity> findByIdP(long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductEntity> products() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        product.ifPresent(productEntity -> productRepository.delete(productEntity));
    }

    @Override
    public Optional<ProductEntity> modiftStock(Long id, int stock) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if(product.isPresent()){
            ProductEntity productDB = product.get();
            productDB.setStock(stock);
            return Optional.of(productRepository.save(productDB));
        }
        else {
            return null ;
        }

    }


}
