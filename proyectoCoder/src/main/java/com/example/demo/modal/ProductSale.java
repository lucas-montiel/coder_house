package com.example.demo.modal;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_sale")
@Getter@Setter
public class ProductSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column
    private Integer count;

    public ProductSale(){

    }

    public ProductSale(SaleEntity sale, ProductEntity product, Integer count) {
        this.sale = sale;
        this.product = product;
        this.count = count;
    }
}
