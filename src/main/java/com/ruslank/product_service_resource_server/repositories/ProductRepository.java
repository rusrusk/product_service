package com.ruslank.product_service_resource_server.repositories;


import com.ruslank.product_service_resource_server.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findByProductName(String name);


}
