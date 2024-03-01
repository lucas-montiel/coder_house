package com.example.demo.modal.dto;

import com.example.demo.modal.ClientEntity;
import com.example.demo.modal.SaleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SaleDto {

    private Long id;
    private Date fehca;
    private ClientEntity client;
    private List<ProductDto> productsCount;
    private Double totalVenta;

    public SaleDto(SaleEntity sale, List<ProductDto> productDtos) {
        this.id = sale.getId();
        this.fehca = sale.getFehca();
        this.client = sale.getClient();
        this.totalVenta = sale.getTotalVenta();
        this.productsCount = productDtos;
    }

    public SaleDto(SaleEntity sale) {
        this.id = sale.getId();
        this.fehca = sale.getFehca();
        this.client = sale.getClient();
        this.totalVenta = sale.getTotalVenta();
        this.productsCount = new ArrayList<>();
    }

}
