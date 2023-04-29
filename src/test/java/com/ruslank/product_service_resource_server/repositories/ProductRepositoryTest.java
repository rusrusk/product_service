package com.ruslank.product_service_resource_server.repositories;

import com.ruslank.product_service_resource_server.entities.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:mysql://localhost/product_service"
})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    @BeforeEach
    void init() {
        product1 = Product.builder()
                .productName("iMac")
                .productPrice(BigDecimal.valueOf(890.5))
                .productQuantity(5)
                .build();
        product2 = Product.builder()
                .productName("Coffeemachine")
                .productPrice(BigDecimal.valueOf(650.5))
                .productQuantity(10)
                .build();
    }

    @Test
    @DisplayName("""
            When [ProductRepository_SaveAllProducts_ReturnSavedProducts]  is called -->
            all the objects should be saved in the database
            """)
    public void ProductRepository_SaveAllProducts_ReturnSavedProducts() {

        //Arrange

        //Act
        Product savedProduct = this.productRepository.save(product1);

        //Assert
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getProductId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("""
            When [ProductRepository_SaveProduct_ReturnSavedProduct]  is called -->
            the object should be saved in the database
            """)
    public void ProductRepository_SaveProduct_ReturnSavedProduct() {
        //Arrange

        //Act
        Product savedProduct = this.productRepository.save(product1);
        //Assert
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getProductId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("""
            When [ProductRepository_FindAllProducts_ReturnAllProducts]  is called -->
            all the specified objects should be fetched from the database
            """)
    public void ProductRepository_FindAllProducts_ReturnAllProducts() {
        //Arrange

        //Act
        this.productRepository.save(product1);
        this.productRepository.save(product2);

        List<Product> listOfProducts = this.productRepository.findAll();

        //Assert
        Assertions.assertThat(listOfProducts).isNotNull();
        Assertions.assertThat(listOfProducts.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("""
            When [ProductRepository_FindProductById_ReturnOneProduct]  is called -->
            the specified object found by ID should be fetched from the database
            """)
    public void ProductRepository_FindProductById_ReturnOneProduct() {
        //Arrange

        //Act
        Product savedProduct1 = this.productRepository.save(product1);
        Product savedProduct2 = this.productRepository.save(product2);

        Product foundProductById = this.productRepository.findById(savedProduct1.getProductId()).get();

        //Assert
        Assertions.assertThat(foundProductById).isNotNull();
        Assertions.assertThat(foundProductById.getProductId()).isEqualTo(product1.getProductId());
        Assertions.assertThat(foundProductById.getProductName()).isEqualTo("iMac");
    }

    @Test
    @DisplayName("""
            When [ProductRepository_FindProduct_ByName_ReturnNotNull]  is called -->
            the specified object found by Name should be fetched from the database
            """)
    public void ProductRepository_FindProduct_ByName_ReturnNotNull() {
        //Arrange

        //Act
        this.productRepository.save(product1);
        this.productRepository.save(product2);

        Product foundProductByName = this.productRepository.findByProductName(product2.getProductName()).get();

        //Assert
        Assertions.assertThat(foundProductByName).isNotNull();
    }

    @Test
    @DisplayName("""
            When [ProductRepository_UpdateProduct_ReturnProductNotNull]  is called -->
            the existing object should be updated found by ID
            """)
    public void ProductRepository_UpdateProduct_ReturnProductNotNull() {
        //Arrange

        //Act
        this.productRepository.save(product1);
        this.productRepository.save(product2);

        Product foundProductById = this.productRepository.findById(product2.getProductId()).get();

        foundProductById.setProductName("Headphones");
        foundProductById.setProductPrice(BigDecimal.valueOf(240.3));
        foundProductById.setProductQuantity(20);

        Product updatedProduct = this.productRepository.save(foundProductById);

        //Assert
        Assertions.assertThat(updatedProduct.getProductName()).isNotNull();
        Assertions.assertThat(updatedProduct.getProductPrice()).isNotNull();
        Assertions.assertThat(updatedProduct.getProductQuantity()).isNotNull();
        Assertions.assertThat(updatedProduct.getProductName()).isEqualTo("Headphones");
    }

    @Test
    @DisplayName("""
            When [ProductRepository_DeleteProductById_ReturnProductIsEmpty]  is called -->
            the existing object should be deleted from the database
            """)
    public void ProductRepository_DeleteProductById_ReturnProductIsEmpty() {
        //Arrange

        //Act
        this.productRepository.save(product1);
        this.productRepository.save(product2);

        this.productRepository.deleteById(product1.getProductId());
        this.productRepository.deleteById(product2.getProductId());
        Optional<Product> productReturn = this.productRepository.findById(product1.getProductId());
        List<Product> productList = this.productRepository.findAll();
        //Assert
        Assertions.assertThat(productReturn).isEmpty();
        Assertions.assertThat(productList.size()).isEqualTo(0);
    }
}
