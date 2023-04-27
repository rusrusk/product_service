package com.ruslank.product_service_project.services;

import com.ruslank.product_service_project.entities.Product;
import com.ruslank.product_service_project.repositories.ProductRepository;
import org.assertj.core.api.Assertions;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("""
            When [ProductService_CreateProduct_ReturnProduct] is called, the object
            should be created and saved in the database.
            """)
    public void ProductService_CreateProduct() {
        Product product = Product.builder()
                .productName("Powerbank")
                .productPrice(BigDecimal.valueOf(330.3))
                .productQuantity(10)
                .build();

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product savedProduct = this.productService.insertProduct(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getProductName()).isEqualTo("Powerbank");
    }

    @Test
    @DisplayName("""
            When [ProductService_CreateProduct_ReturnProduct] is called, all the objects
            should be fetched and list size of 2 returned.
            """)
    public void ProductService_FindAllProducts() {
        Product product1 = Product.builder()
                .productName("iMac")
                .productPrice(BigDecimal.valueOf(890.5))
                .productQuantity(5)
                .build();

        Product product2 = Product.builder()
                .productName("Coffeemachine")
                .productPrice(BigDecimal.valueOf(650.5))
                .productQuantity(10)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(this.productRepository.findAll()).thenReturn(productList);

        List<Product> productServiceList = this.productService.findAllProducts();

        Assertions.assertThat(productServiceList).isNotNull();
        Assertions.assertThat(productServiceList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("""
            When [ProductService_FindProductById] is called,  object
            should be fetched from the database found by ID
            """)
    public void ProductService_FindProductById() {

        Product product1 = Product.builder()
                .productId(1)
                .productName("iMac")
                .productPrice(BigDecimal.valueOf(890.5))
                .productQuantity(5)
                .build();
        this.productRepository.save(product1);

        when(this.productRepository.findById(anyLong())).thenReturn(Optional.of(product1));
        Product foundProductById = this.productService.findProductById(1L);

        Assertions.assertThat(foundProductById).isNotNull();
        Assertions.assertThat(foundProductById.getProductId()).isEqualTo(1);
    }

    @Test
    @DisplayName("""
            When [ProductService_FindProductByName] is called,  object
            should be fetched from the database found by Name
            """)
    public void ProductService_FindProductByName() {
        Product product1 = Product.builder()
                .productId(1)
                .productName("iMac")
                .productPrice(BigDecimal.valueOf(890.5))
                .productQuantity(5)
                .build();

        when(this.productRepository.findByProductName(anyString())).thenReturn(Optional.of(product1));

        Product foundProductByName = this.productService.findProductByName("IMac");

        Assertions.assertThat(foundProductByName).isNotNull();
        Assertions.assertThat(foundProductByName.getProductName()).isEqualTo("iMac");
    }

    @Test
    @DisplayName("""
            When [ProductService_FindProductByIdForException] is called,  
            the exception should be thrown
            """)
    public void ProductService_FindProductByIdForException() {
        Product product1 = Product.builder()
                .productId(1)
                .productName("iMac")
                .productPrice(BigDecimal.valueOf(890.5))
                .productQuantity(5)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            this.productService.findProductById(2L);
        });
    }

    @Test
    @DisplayName("""
            When [ProductService_UpdateProductFoundById] is called,  
            given object should be updated
            """)
    void ProductService_UpdateProductFoundById() {
        Product product1 = Product.builder()
                .productId(1)
                .productName("iMac")
                .productPrice(BigDecimal.valueOf(890.5))
                .productQuantity(5)
                .build();

        when(this.productRepository.findById(anyLong())).thenReturn(Optional.of(product1));
        when(this.productRepository.save(Mockito.any(Product.class))).thenReturn(product1);

        product1.setProductName("Dell");
        Product updatedProduct = this.productService.updateProduct(1L, product1);

        Assertions.assertThat(updatedProduct).isNotNull();
        Assertions.assertThat(updatedProduct.getProductName()).isEqualTo("Dell");
    }
}
