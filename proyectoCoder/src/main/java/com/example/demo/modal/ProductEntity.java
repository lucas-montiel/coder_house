package com.example.demo.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="product")
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int stock;

    @Column
    private String nombre;

    @ManyToMany(mappedBy = "products")
    private Set<SaleEntity> sale = new HashSet<>();

    @Column
    private Float price;
}
