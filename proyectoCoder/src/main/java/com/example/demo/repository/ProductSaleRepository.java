package com.example.demo.repository;

import com.example.demo.modal.ProductSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductSaleRepository extends JpaRepository<ProductSale, Long> {


    @Query(value = "SELECT * FROM product_sale WHERE sale_id = :idSale AND product_id = :idProduct" , nativeQuery = true)
    ProductSale findProductSale(@Param("idSale") Long sale, @Param("idProduct") Long product);
}
