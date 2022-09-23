package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.ClientDTO;
import com.devsimple.helpdesk.exceptions.ObjectNotFoundException;
import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", " ");
    }

    public ClientDTO findById(String id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
        return new ClientDTO(client);
    }

    public List<ClientDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(x -> new ClientDTO(x))
                .collect(Collectors.toList());
    }
}
