package com.warehouse.warehouse.repository;

import com.warehouse.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


 @Query("SELECT p FROM Product p WHERE p.name = :name")
List<Product> findByGivenName(@Param("name") String name);


}
