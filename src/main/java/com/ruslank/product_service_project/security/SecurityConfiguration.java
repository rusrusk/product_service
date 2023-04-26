package com.ruslank.product_service_project.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        

        return httpSecurity.build();
    }
}
