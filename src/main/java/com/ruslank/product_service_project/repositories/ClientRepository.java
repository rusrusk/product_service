package com.ruslank.product_service_project.repositories;

import com.ruslank.product_service_project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.clientId = :clientId")
    public Optional<Client> findByClientId(String clientId);
}
