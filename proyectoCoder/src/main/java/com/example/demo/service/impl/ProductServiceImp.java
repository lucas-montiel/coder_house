package com.example.demo.service.impl;

import com.example.demo.modal.ProductEntity;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public ProductEntity create(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<ProductEntity> findByIdP(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<ProductEntity> products() {
        List<ProductEntity> lista = productRepository.findAllProduct();
        return lista;
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        product.ifPresent(productEntity -> productRepository.delete(productEntity));
    }

    @Override
    @Transactional
    public Optional<ProductEntity> modiftStock(Long id, int stock) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if(product.isPresent()){
            ProductEntity productDB = product.get();
            productDB.setStock(stock);
            return Optional.of(productRepository.save(productDB));
        }
        else {
            return Optional.empty() ;
        }

    }

    @Override
    public Optional<ProductEntity> modifyPrice(Long id, Double price) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if(product.isPresent()){
            ProductEntity productdb = product.get();
            productdb.setPrice(price);
            return Optional.of(productRepository.save(productdb));

        }else{
            return Optional.empty();
        }
    }


}
