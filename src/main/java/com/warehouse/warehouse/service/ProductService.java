package com.warehouse.warehouse.service;


import com.warehouse.warehouse.model.Company;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.CompanyRepository;
import com.warehouse.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository
    ) {
        this.productRepository = productRepository;

    }


    public List<Product> findProductsExpensiveThanGivenPrice(Double givenPrice) {

        return productRepository.findAll().stream()
                .filter(product -> product.getPrice() > givenPrice)
                .collect(Collectors.toList());
    }


    public List<Product> delete(Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }


    public List<Product> findProductHeavierThanGivenWeight(Double givenWeight) {

        List<Product> productsHeaverThanGivenWeight = productRepository.findAll().stream()
                .filter(product -> product.getWeight() > givenWeight)
                .collect(Collectors.toList());
        return productsHeaverThanGivenWeight;
    }


}
