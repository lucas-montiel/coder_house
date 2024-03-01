package com.example.demo.service.impl;

import com.example.demo.modal.ClientEntity;
import com.example.demo.modal.ProductEntity;
import com.example.demo.modal.ProductSale;
import com.example.demo.modal.SaleEntity;
import com.example.demo.modal.dto.ProductDto;
import com.example.demo.modal.dto.ProductRequest;
import com.example.demo.modal.dto.SaleDto;
import com.example.demo.modal.dto.SaleRequest;
import com.example.demo.repository.ProductSaleRepository;
import com.example.demo.repository.SaleRepository;
import com.example.demo.service.ClientService;
import com.example.demo.service.ProductService;
import com.example.demo.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SaleServiceImp implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSaleRepository productSaleRepository;


    /**
     *  Creamos el ticket a partir del id del usuario, id del producto y la cantidad a comprar
     * @param sale
     * @return Object
     */
    @Override
    @Transactional
    public Object create(SaleRequest sale) {
        SaleEntity ticket = new SaleEntity();
        Optional<ClientEntity> client = clientService.buscarPorId(sale.getIdClient());
        List<ProductEntity> listProduct = new ArrayList<>();
        List<ProductDto> productDtos = new ArrayList<>();
        double total = 0.0;

        if(client.isPresent()){
            ticket.setClient(client.get());
            for(ProductRequest productRequest : sale.getProducts()){
                Optional<ProductEntity> product = productService.findByIdP(productRequest.getIdProduct());
                if(product.isPresent()){
                    if(product.get().getStock()>=productRequest.getCount()){
                        ProductDto productDto = new ProductDto(product.get(), productRequest.getCount());
                        productDtos.add(productDto);
                        listProduct.add(product.get());
                        total=total + product.get().getPrice() * productRequest.getCount();
                    }
                }else{
                    return null;
                }
             }
            if(!listProduct.isEmpty()) {

                for (int i = 0; i < listProduct.size(); i++) {
                    List<ProductEntity> list = new ArrayList<>(listProduct);
                    ProductEntity productEntity = list.get(i);
                    productEntity.setStock(productEntity.getStock() - sale.getProducts().get(i).getCount());
                    productService.modiftStock(productEntity.getId(), productEntity.getStock());
                }
                ticket.setProducts(listProduct);
                ticket.setFehca(new Date());
                ticket.setTotalVenta(total);
                SaleEntity saleEntity = saleRepository.save(ticket);

                return new SaleDto(saleEntity, productDtos);
            }
        }
        return null;
    }


    public void modifySaleProduct(SaleDto saleDto){
        Long sale = saleDto.getId();
        for (ProductDto productDto : saleDto.getProductsCount()) {
            Long product = productDto.getId();
            ProductSale productSale = productSaleRepository.findProductSale(sale, product);
            productSale.setCount(productDto.getCount());
            productSaleRepository.save(productSale);
        }
    }



    @Override
    public List<SaleEntity> allSales(){

        List<SaleEntity> listSaleEntity = saleRepository.findAll();

        List<SaleDto> listDto = new ArrayList<>();
        for (SaleEntity sale: listSaleEntity) {
            SaleDto saleDto = new SaleDto(sale);

           // for (ProductEntity product: sale.getProducts()) {
                //Optional<ProductEntity> productEntity = productService.findByIdP(product.getIdProduct());
               // if(productEntity.isPresent()){
                //    ProductDto productDto = new ProductDto(productEntity.get(), product.getCount());
               //     saleDto.productAdd(productDto);
               // }
            //}
            listDto.add(saleDto);
        }
        return listSaleEntity;
    }
}
