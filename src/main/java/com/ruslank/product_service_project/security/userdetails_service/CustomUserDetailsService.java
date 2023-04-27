package com.ruslank.product_service_project.security.userdetails_service;

import com.ruslank.product_service_project.entities.User;
import com.ruslank.product_service_project.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Given username was not found"));
    }

    public void createUser(User user) {
        Optional<User> createdUser = this.userRepository.findByUsername(user.getUsername());

        if (createdUser.isPresent()) {
            throw new UsernameNotFoundException("User is present");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }
}
