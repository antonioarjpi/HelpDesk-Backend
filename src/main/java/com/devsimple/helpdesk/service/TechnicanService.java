package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.exceptions.DataIntegratyViolationException;
import com.devsimple.helpdesk.exceptions.ObjectNotFoundException;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.User;
import com.devsimple.helpdesk.repository.TechnicianRepository;
import com.devsimple.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return UUID.randomUUID().toString().replace("-", "");
    }

    public Technician findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
    }

    public TechnicianDTO findByIdDTO(String id) {
        Technician technician = findById(id);
        return new TechnicianDTO(technician);
    }

    @Transactional
    public List<TechnicianDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(x -> new TechnicianDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional
    public Technician save(TechnicianDTO technicianDTO) {
        technicianDTO.setId(getUUid());
        validateCPFandEmail(technicianDTO);
        Technician technician = new Technician(technicianDTO);
        return repository.save(technician);
    }

    @Transactional
    public Technician update(String id, TechnicianDTO dto) {
        dto.setId(id);
        Technician oldTech = findById(id);
        validateCPFandEmail(dto);
        oldTech = new Technician(dto);
        return repository.save(oldTech);
    }

    @Transactional
    public void delete(String id){
        Technician findTechnician = findById(id);
        if (findTechnician.getCalleds().size() > 0){
            throw new DataIntegratyViolationException("Técnico que possui chamados não pode ser deletado.");
        }
        repository.deleteById(id);
    }

    private void validateCPFandEmail(TechnicianDTO dto) {
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
