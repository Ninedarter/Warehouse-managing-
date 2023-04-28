package com.warehouse.warehouse.config;

import com.warehouse.warehouse.model.Company;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.CompanyRepository;
import com.warehouse.warehouse.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class AppConfig {
    @Bean
    CommandLineRunner createInitialDbRecords(CompanyRepository companyRepository, ProductRepository productRepository) {
        return args -> {
            Company samsung = Company.builder()
                    .name("Samsungas")
                    .budged(250000l)
                    .numberOfEmployees(5000)
                    .build();

            companyRepository.save(samsung);


            Company adata = Company.builder()
                    .name("ADATA")
                    .budged(120000l)
                    .numberOfEmployees(100)
                    .build();

            companyRepository.save(adata);

            Product galaxyS23 = Product.builder()
                    .name("telefonas")
                    .price(1000.1)
                    .company(samsung)
                    .quantityInStock(15.0)
                    .weight(1.2)
                    .build();

            Product smartTV = Product.builder()
                    .name("Samsung SMART TV")
                    .price(2500.0)
                    .company(samsung)
                    .quantityInStock(20.0)
                    .weight(2.2)
                    .build();

            Product ssdHard = Product.builder()
                    .name("SSD HARD DRIVE 250GB")
                    .price(150.0)
                    .quantityInStock(80.0)
                    .company(adata)
                    .weight(4.2)
                    .build();

            Product ram16GB = Product.builder()
                    .name("RAM 16 GB")
                    .price(39.0)
                    .quantityInStock(120.0)
                    .company(adata)
                    .weight(6.2)
                    .build();

            productRepository.saveAll(Arrays.asList(ssdHard, ram16GB, smartTV, galaxyS23));


        };
    }


}
