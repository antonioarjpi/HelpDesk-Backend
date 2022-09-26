package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.exceptions.DataIntegratyViolationException;
import com.devsimple.helpdesk.exceptions.ObjectNotFoundException;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.User;
import com.devsimple.helpdesk.repository.TechnicianRepository;
import com.devsimple.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TechnicanService {

    private TechnicianRepository repository;
    private UserRepository userRepository;

    public TechnicanService(TechnicianRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", " ");
    }

    public TechnicianDTO findById(String id) {
        Technician technician = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
        return new TechnicianDTO(technician);
    }

    public List<TechnicianDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(x -> new TechnicianDTO(x))
                .collect(Collectors.toList());
    }

    public Technician save(TechnicianDTO technicianDTO) {
        technicianDTO.setId(getUUid());
        validateCPFandEmail(technicianDTO);
        Technician technician = new Technician(technicianDTO);
        return repository.save(technician);
    }

    private void validateCPFandEmail(TechnicianDTO dto) {
        Optional<User> byCpf = userRepository.findByCpf(dto.getCpf());
        if (byCpf.isPresent() && byCpf.get().getId() != dto.getId()) {
            throw new DataIntegratyViolationException("CPF já cadastrado");
        }
        Optional<User> byEmail = userRepository.findByEmail(dto.getEmail());
        if (byEmail.isPresent()) {
            throw new DataIntegratyViolationException("E-mail já cadastrado");
        }
    }
}
