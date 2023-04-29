package com.ruslank.product_service_resource_server.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public class CustomJwtConverter implements Converter<Jwt, JwtAuthenticationToken> {
    @Override
    public JwtAuthenticationToken convert(Jwt source) {
        List<String> grantedAuthorityList = (List<String>) source.getClaims().get("authorities");
        JwtAuthenticationToken token = new JwtAuthenticationToken(source, grantedAuthorityList
                .stream()
                .map(SimpleGrantedAuthority::new).toList());
        return token;
    }
}
