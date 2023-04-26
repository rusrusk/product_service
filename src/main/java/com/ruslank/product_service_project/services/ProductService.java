package com.ruslank.product_service_project.services;

import com.ruslank.product_service_project.entities.Product;
import com.ruslank.product_service_project.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Product findProductByName(String name) {
        return this.productRepository.findByProductName(name)
                .orElseThrow(() -> new RuntimeException("Product doesn't exist"));
    }

    public Product insertProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product product) {
        Product existingProduct =
                this.productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("no product to update"));
        if ((!"null".equals(existingProduct)) || (existingProduct != null)) {
            if (product.getProductName() != null)
                existingProduct.setProductName(product.getProductName());
            if (product.getProductPrice() != null)
                existingProduct.setProductPrice(product.getProductPrice());
            if (product.getProductQuantity() != null)
                existingProduct.setProductQuantity(product.getProductQuantity());
        }
        return this.productRepository.save(existingProduct);
    }

    public String deleteProduct(Long productId) {
        Product existing = this.productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("no product to delete"));
        this.productRepository.deleteById(productId);
        return "the PRODUCT IS DELETED";
    }
}
