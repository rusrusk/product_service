package com.ruslank.product_service_project.repositories;

import com.ruslank.product_service_project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByProductName(String name);


}
