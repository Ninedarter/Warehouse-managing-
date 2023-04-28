package com.warehouse.warehouse.controller;

import com.warehouse.warehouse.model.Company;
import com.warehouse.warehouse.service.CompanyService;
import com.warehouse.warehouse.service.PdfService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")

public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAll() {

        try {
            List<Company> allCompanies = companyService.getAll();
            logger.info(allCompanies.size() + " companies were found");
            pdfService.convertToPdf(allCompanies);
            return new ResponseEntity<>(allCompanies, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{name}")
    public ResponseEntity<Company> findByName(@PathVariable String name) {
        return new ResponseEntity<>(companyService.findByName(name), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<List<Company>> addNew(@RequestBody Company company) {

        try {
            List<Company> companies = companyService.addNew(company);
            logger.info(company.getName() + " company was added succesfully");

            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Company>> delete(@PathVariable Long id){

        try{
            List<Company> companiesAfterDelete = companyService.delete(id);
            logger.info("company was deleted successfully");
            return new ResponseEntity<>(companiesAfterDelete, HttpStatus.OK);
        }catch (Exception ex){
            logger.error("Something went wrong!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
