package com.example.demo.controllers;

import com.example.demo.modal.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Crea un producto
     * @param product
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> creatProduct(@RequestBody ProductEntity product){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Busca todos los productos de la base de datos
     * @return
     */
    @GetMapping("/findProducts")
    public ResponseEntity<?> findAllProducts (){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.products());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Busca un producto por Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> product(@PathVariable long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(productService.findByIdP(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Borra un producto por id
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        try{
            Optional<ProductEntity> product = productService.findByIdP(id);
            if(product.isPresent()){
                productService.deleteProduct(id);
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe ese producto en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Modifica el stock del producto en la base de datos
     * @param id
     * @param stock
     * @return
     */
    @PutMapping("/modifyStock/{id}/{stock}")
    @Transactional
    public ResponseEntity<?> modifyStock(@PathVariable Long id, @PathVariable int stock){
        try {
            Optional<ProductEntity> product = productService.findByIdP(id);
            if(product.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(productService.modiftStock(id,stock));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe ese producto en la base de datos");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Se modifica el precio del produto en la bae de datos
     * @param id
     * @param price
     * @return
     */
    @PutMapping("/modifyPrice/{id}/{price}")
    @Transactional
    public ResponseEntity<?> modifyPrice(@PathVariable Long id, @PathVariable Double price){
        try {
            Optional<ProductEntity> product = productService.findByIdP(id);
            if(product.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(productService.modifyPrice(id,price));
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe ese producto en la base de datos");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
