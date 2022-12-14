package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.dto.TechnicianUpdateDTO;
import com.devsimple.helpdesk.exceptions.DataIntegratyViolationException;
import com.devsimple.helpdesk.exceptions.ObjectNotFoundException;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.User;
import com.devsimple.helpdesk.repository.TechnicianRepository;
import com.devsimple.helpdesk.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder passwordEncoder;

    public TechnicanService(TechnicianRepository repository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        technicianDTO.setPassword(passwordEncoder.encode(technicianDTO.getPassword()));
        validateCPFandEmail(technicianDTO);
        Technician technician = new Technician(technicianDTO);
        return repository.save(technician);
    }

    @Transactional
    public Technician update(String id, TechnicianUpdateDTO dto) {
        dto.setId(id);
        Technician oldTech = findById(id);
        String password = oldTech.getPassword();
        validateCPFandEmailUpdate(dto);
        oldTech = new Technician(dto);
        oldTech.setPassword(password);
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
        Optional<User> userOptional = userRepository.findByCpf(dto.getCpf());
        if (userOptional.isPresent() && userOptional.get().getId() != dto.getId()) {
            throw new DataIntegratyViolationException("CPF já cadastrado");
        }
        userOptional = userRepository.findByEmail(dto.getEmail());
        if (userOptional.isPresent() && userOptional.get().getId() != dto.getId()) {
            throw new DataIntegratyViolationException("E-mail já cadastrado");
        }
    }

    private void validateCPFandEmailUpdate(TechnicianUpdateDTO dto) {
        Optional<User> userOptional = userRepository.findByCpf(dto.getCpf());
        if (userOptional.isPresent() && userOptional.get().getId() != dto.getId()) {
            throw new DataIntegratyViolationException("CPF já cadastrado");
        }
        userOptional = userRepository.findByEmail(dto.getEmail());
        if (userOptional.isPresent() && userOptional.get().getId() != dto.getId()) {
            throw new DataIntegratyViolationException("E-mail já cadastrado");
        }
    }
}
