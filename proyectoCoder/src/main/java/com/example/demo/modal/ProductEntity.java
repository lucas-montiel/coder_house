package com.example.demo.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    @JsonIgnore
    private List<SaleEntity> sale;

    @Column
    private Double price;
}
