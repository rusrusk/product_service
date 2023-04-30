package com.ruslank.product_service_project.cors;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import java.util.Arrays;
//
@Configuration
public class CustomCorsHandler implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**");
    }
}
//
//    public void corsHandler(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.cors(
//                c -> {
//                    CorsConfigurationSource source = s -> {
//                        CorsConfiguration corsConfiguration = new CorsConfiguration();
//                        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3003"));
//                        corsConfiguration.setAllowCredentials(true);
//                        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
//                        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
//                        return corsConfiguration;
//                    };
//                    c.configurationSource(source);
//                }
//        );
//    }
//
//
//}
