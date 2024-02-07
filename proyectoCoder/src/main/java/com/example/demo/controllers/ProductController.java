package com.example.demo.controllers;

import com.example.demo.modal.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> creatProduct(@RequestBody ProductEntity product){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/findClients")
    public ResponseEntity<?> findAllProducts (){
        return ResponseEntity.status(HttpStatus.OK).body(productService.products());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> product(@PathVariable long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.findByIdP(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        try{
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



    @GetMapping("/edad/{id}/{stock}")
    public ResponseEntity<?> modifyStock(@PathVariable Long id, @PathVariable int stock){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.modiftStock(id, stock));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
