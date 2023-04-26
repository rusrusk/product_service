package com.ruslank.product_service_project.repositories;

import com.ruslank.product_service_project.entities.Product;
import org.assertj.core.api.Assertions;
import org.hibernate.dialect.Database;
import org.hibernate.dialect.MySQLDialect;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:mysql://localhost/product_service"
})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void ProductRepository_SaveAllProducts_ReturnSavedProducts() {

        //Arrange
        Product product = Product.builder()
                .productName("Iphone")
                .productPrice(BigDecimal.valueOf(650.5))
                .productQuantity(10)
                .build();

        //Act
        Product savedProduct = this.productRepository.save(product);

        //Assert
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getProductId()).isGreaterThan(0);
    }

    @Test
    public void ProductRepository_FindAllProducts_ReturnAllProducts() {
        //Arrange
        Product product1 = Product.builder()
                .productName("IMAC")
                .productPrice(BigDecimal.valueOf(650.5))
                .productQuantity(10)
                .build();
        Product product2 = Product.builder()
                .productName("IMAC1")
                .productPrice(BigDecimal.valueOf(660.5))
                .productQuantity(11)
                .build();

        //Act
        Product savedProduct1 = this.productRepository.save(product1);
        Product savedProduct2 = this.productRepository.save(product2);

        List<Product> listOfProducts = this.productRepository.findAll();

        //Assert
        Assertions.assertThat(listOfProducts).isNotNull();
        Assertions.assertThat(listOfProducts.size()).isEqualTo(2);
    }

    @Test
    public void ProductRepository_FindProductById_ReturnOneProduct() {
        //Arrange
        Product product1 = Product.builder()
                .productName("IMAC")
                .productPrice(BigDecimal.valueOf(650.5))
                .productQuantity(10)
                .build();
        Product product2 = Product.builder()
                .productName("IMAC1")
                .productPrice(BigDecimal.valueOf(660.5))
                .productQuantity(11)
                .build();

        //Act
        Product savedProduct1 = this.productRepository.save(product1);
        Product savedProduct2 = this.productRepository.save(product2);

        Product foundProductById = this.productRepository.findById(savedProduct1.getProductId()).get();

        //Assert
        Assertions.assertThat(foundProductById).isNotNull();
        Assertions.assertThat(foundProductById.getProductId()).isEqualTo(product1.getProductId());
    }
}
