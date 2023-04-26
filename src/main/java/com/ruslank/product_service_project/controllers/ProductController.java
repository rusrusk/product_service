package com.ruslank.product_service_project.controllers;

import com.ruslank.product_service_project.entities.Product;
import com.ruslank.product_service_project.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public List<Product> findAllProducts() {
        return this.productService.findAllProducts();
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable String name) {
        return new ResponseEntity<Product> (this.productService.findProductByName(name), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(this.productService.insertProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<Product>(this.productService.updateProduct(id, product), HttpStatus.CREATED);
    }

    @DeleteMapping("/product/{id}")
     public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<String>(this.productService.deleteProduct(id), HttpStatus.OK);
    }
}
