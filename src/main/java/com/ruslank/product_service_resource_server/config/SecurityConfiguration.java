package com.ruslank.product_service_resource_server.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Value("${jwkUri}")
    private String jwkUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.oauth2ResourceServer(
                r -> r.jwt().jwkSetUri(jwkUri)
                        .jwtAuthenticationConverter(new CustomJwtConverter())
        );



        httpSecurity
                .authorizeHttpRequests().anyRequest().authenticated();

        return httpSecurity.build();
    }
}
