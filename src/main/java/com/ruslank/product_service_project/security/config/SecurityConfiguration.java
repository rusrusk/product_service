package com.ruslank.product_service_project.security.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
//import com.ruslank.product_service_project.cors.CustomCorsHandler;
import com.ruslank.product_service_project.security.keys.JWKManager;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final JWKManager jwkManager;
//    private final CustomCorsHandler customCorsHandler;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.cors();

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);
        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

//        if the user is not authenticated yet, he will be redirected to /login
        httpSecurity.exceptionHandling(
                e -> e.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))

        );

        return httpSecurity
                .build();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource()
//    {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        //or any domain that you want to restrict to
//        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept","Authorization"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        //Add the method support as you like
//        UrlBasedCorsConfigurationSource source = new     UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        List<String> allowOrigins = Arrays.asList("*");
//        configuration.setAllowedOrigins(allowOrigins);
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        //in case authentication is enabled this flag MUST be set, otherwise CORS requests will fail
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    @Order(2)
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.cors().and().formLogin()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .build();
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**");
////                        .allowedOriginPatterns(CorsConfiguration.ALL)
////                        .allowedMethods(CorsConfiguration.ALL)
////                        .allowedHeaders(CorsConfiguration.ALL);
////                        .exposedHeaders("Access-Control-Allow-Origin") // add this line
////                        .allowCredentials(true)
////                        .maxAge(3600)
////                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//            }
//        };
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        JWKSet jwkSet = new JWKSet(jwkManager.rsaKey());
        return new ImmutableJWKSet(jwkSet);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> customizer() {
        return context -> {
            var authorities = context.getPrincipal().getAuthorities();

            context.getClaims().claim("authorities", authorities
                    .stream().map(a -> a.getAuthority()).toList());
        };
    }
}

// http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=http://localhost:3003/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256