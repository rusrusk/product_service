package com.ruslank.product_service_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruslank.product_service_project.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;



    @Test
    @DisplayName("""
            when /products endpoint is called, the HTTP status code 200 should be returned
            """)
    public void testFindAllProducts() throws Exception {
        mockMvc
                .perform(get("/products"))
                .andExpect(status().isOk());
    }

//    @Test
//    @DisplayName("""
//            when /products/{name} endpoint is called, the HTTP status code 200 should be returned
//            """)
//    public void testFindProductByName() throws Exception {
//        mockMvc
//                .perform(get("/products/{name}"))
//    }

    public void ProductController_InsertProduct_ReturnInserted() throws Exception {

    }
}
