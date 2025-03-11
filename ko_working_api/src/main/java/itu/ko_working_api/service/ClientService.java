package itu.ko_working_api.service;

import itu.ko_working_api.entity.Client;
import itu.ko_working_api.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    // save
    @Transactional
    public Client save(Client client) {
        String nextId = clientRepository.generateId();
        client.setIdClient(nextId);

        return clientRepository.save(client);
    }

    // find
    public Optional<Client> findByNumero(String numero) {
        return clientRepository.findByNumero(numero);
    }
}
