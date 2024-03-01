package com.example.demo.modal.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Getter@Setter
public class SaleRequest {

    private Long idClient;

    private ArrayList<ProductRequest> products;

}
