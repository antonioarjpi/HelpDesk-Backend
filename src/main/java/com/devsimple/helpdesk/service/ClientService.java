package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.ClientDTO;
import com.devsimple.helpdesk.exceptions.DataIntegratyViolationException;
import com.devsimple.helpdesk.exceptions.ObjectNotFoundException;
import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.User;
import com.devsimple.helpdesk.repository.ClientRepository;
import com.devsimple.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository repository;
    private UserRepository userRepository;

    public ClientService(ClientRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public ClientDTO findByIdDTO(String id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional
    public List<ClientDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(x -> new ClientDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional
    public Client save(ClientDTO clientDTO) {
        clientDTO.setId(getUUid());
        validateCPFandEmail(clientDTO);
        Client client = new Client(clientDTO);
        return repository.save(client);
    }

    @Transactional
    public Client update(String id, ClientDTO dto) {
        dto.setId(id);
        Client oldClient = findById(id);
        validateCPFandEmail(dto);
        oldClient = new Client(dto);
        return repository.save(oldClient);
    }

    @Transactional
    public void delete(String id){
        Client findClient = findById(id);
        if (findClient.getCalleds().size() > 0){
            throw new DataIntegratyViolationException("Cliente que possui chamados não pode ser deletado");
        }
        repository.deleteById(id);
    }

    private Client findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    private void validateCPFandEmail(ClientDTO dto) {
        Optional<User> byCpf = userRepository.findByCpf(dto.getCpf());
        if (byCpf.isPresent() && byCpf.get().getId() != dto.getId()) {
            throw new DataIntegratyViolationException("CPF já cadastrado");
        }
        Optional<User> byEmail = userRepository.findByEmail(dto.getEmail());
        if (byEmail.isPresent() && byCpf.get().getId() != dto.getId()) {
            throw new DataIntegratyViolationException("E-mail já cadastrado");
        }
    }
}
