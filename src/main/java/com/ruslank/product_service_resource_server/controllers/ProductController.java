package com.ruslank.product_service_resource_server.controllers;

import com.ruslank.product_service_resource_server.entities.Product;
import com.ruslank.product_service_resource_server.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public List<Product> findAllProducts() {
        return this.productService.findAllProducts();
    }

    @GetMapping("/product/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable String name) {
        return new ResponseEntity<Product> (this.productService.findProductByName(name), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long productId) {
        return new ResponseEntity<Product>(this.productService.findProductById(productId), HttpStatus.OK);
    }

    @PostMapping("/product/create")
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(this.productService.insertProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return new ResponseEntity<Product>(this.productService.updateProduct(id, product), HttpStatus.CREATED);
    }

    @DeleteMapping("/product/delete{id}")
     public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<String>(this.productService.deleteProduct(id), HttpStatus.OK);
    }
}
