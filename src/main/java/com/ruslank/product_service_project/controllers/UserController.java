package com.ruslank.product_service_project.controllers;

import com.ruslank.product_service_project.entities.User;
import com.ruslank.product_service_project.security.userdetails_service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        this.customUserDetailsService.createUser(user);
    }
}
