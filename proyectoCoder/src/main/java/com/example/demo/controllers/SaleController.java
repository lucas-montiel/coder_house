package com.example.demo.controllers;

import com.example.demo.modal.dto.SaleDto;
import com.example.demo.modal.dto.SaleRequest;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;


    /**
     * Crea un ticket y guarda la cantidad en la tabla intermedia entre product y sale
     * @param sale
     * @return
     */
    @PutMapping("/create")
    public ResponseEntity<?> create(@RequestBody SaleRequest sale){
        try {
            SaleDto saleDto = (SaleDto) saleService.create(sale);
            if(saleDto == null){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("No hay stock del producto");
            }
            saleService.modifySaleProduct(saleDto);
            return ResponseEntity.ok().body(saleDto);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Devuelve todos los ticket
     * @return
     */
    @GetMapping("/sales")
    public ResponseEntity<?> sales(){
        try {
            return ResponseEntity.ok().body(saleService.allSales());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
