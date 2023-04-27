package com.ruslank.product_service_project.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.ruslank.product_service_project.security.keys.JWKManager;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {

    private final JWKManager jwkManager;

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

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

    @Bean
    @Order(2)
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .build();
    }

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

}
