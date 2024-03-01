package com.example.demo.service;

import com.example.demo.modal.dto.SaleDto;
import com.example.demo.modal.dto.SaleRequest;

public interface SaleService {
    Object create(SaleRequest sale);

    Object allSales();

    void modifySaleProduct(SaleDto saleDto);
}
