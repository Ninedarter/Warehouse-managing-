package com.warehouse.warehouse.controller;


import com.warehouse.warehouse.model.Company;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.ProductRepository;
import com.warehouse.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{givenPrice}")

    public List<Product> findProductsGreaterThanGivenPrice(@PathVariable Double givenPrice) {

        return productService.findProductsExpensiveThanGivenPrice(givenPrice);
    }

    @GetMapping("/name={name}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String name) {

        return new ResponseEntity<>(productRepository.findByGivenName(name), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Product>> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/weight={weight}")
    public ResponseEntity<List<Product>> findHeavierThan(@PathVariable Double weight){
         return new ResponseEntity<>(productService.findProductHeavierThanGivenWeight(weight), HttpStatus.OK);
    }

}
