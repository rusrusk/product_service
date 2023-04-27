package com.ruslank.product_service_project.security.clientdetails_service;

import com.ruslank.product_service_project.entities.Client;
import com.ruslank.product_service_project.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        this.clientRepository.save(Client.from(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        var client = this.clientRepository.findById(Long.valueOf(id))
                .orElseThrow();
        return Client.from(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var client = this.clientRepository.findByClientId(clientId)
                .orElseThrow();
        return Client.from(client);
    }
}
