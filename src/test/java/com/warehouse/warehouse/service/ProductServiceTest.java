package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.CompanyRepository;
import com.warehouse.warehouse.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock // si atonacija sukuria fake/mocked repositorijos instance
    private ProductRepository productRepository;

    @InjectMocks // Injectina arba inskiepija visus Mockus i duotaji instance
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        // reikia kiekvienam testui,kur naudoju MOCKUS
        MockitoAnnotations.openMocks(this);
    }

    @Test
    //turime MOCKDATA (fake duomenys pasidaryti) nes shoolrepository.findAll negalima patestuoti
    void shouldFindProductsWhichPriceGreaterThan20()

    {
        Product product1 = new Product(1l, "telefonas", 10.0, 5.0, null);
        Product product2 = new Product(2l, "kompiuteris", 20.0, 7.0, null);
        Product product3 = new Product(3l, "kolonele", 30.0, 10.0, null);
        Product product4 = new Product(4l, "pele", 40.0, 11.0, null);
        List<Product> products = Arrays.asList(product1, product2, product3, product4);


        when(productRepository.findAll()).thenReturn(products);
        List<Product> filteredProducts = productService.findProductsExpensiveThanGivenPrice(20.0);
        assertEquals(2, filteredProducts.size());
        assertEquals(product4, filteredProducts.get(1));
        assertEquals("kolonele",filteredProducts.get(0).getName());

    }
}