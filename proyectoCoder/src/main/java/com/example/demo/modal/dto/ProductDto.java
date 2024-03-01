package com.example.demo.modal.dto;

import com.example.demo.modal.ProductEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDto {

    private Long id;
    private int count;
    private String nombre;
    private Double price;

    public ProductDto(ProductEntity product, int count) {
        this.id = product.getId();
        this.count = count;
        this.nombre = product.getNombre();
        this.price = product.getPrice();
    }
}
