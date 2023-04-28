package com.warehouse.warehouse.service;

import com.warehouse.warehouse.model.Company;
import com.warehouse.warehouse.model.Product;
import com.warehouse.warehouse.repository.CompanyRepository;
import com.warehouse.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyService {


    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;

    public CompanyService(CompanyRepository companyRepository, ProductRepository productRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
    }


    public List<Company> getAll() {

        return companyRepository.findAll();
    }

    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }

    @Transactional
    public List<Company> addNew(Company company) {
        companyRepository.save(company);
        company.getProducts().forEach(product -> product.setCompany(company));
        productRepository.saveAll(company.getProducts());
        return companyRepository.findAll();
    }


    public List<Company> delete(Long id){
        companyRepository.deleteById(id);
        return companyRepository.findAll();

    }


}
