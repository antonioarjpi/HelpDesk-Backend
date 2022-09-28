package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.CalledCreateDTO;
import com.devsimple.helpdesk.dto.CalledDTO;
import com.devsimple.helpdesk.exceptions.ObjectNotFoundException;
import com.devsimple.helpdesk.model.Called;
import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.enums.Priority;
import com.devsimple.helpdesk.model.enums.Status;
import com.devsimple.helpdesk.repository.CalledRepository;
import com.devsimple.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CalledService {

    private CalledRepository repository;
    private ClientService clientService;
    private TechnicanService technicanService;
    private UserRepository userRepository;

    public CalledService(CalledRepository repository, ClientService clientService, TechnicanService technicanService, UserRepository userRepository) {
        this.repository = repository;
        this.clientService = clientService;
        this.technicanService = technicanService;
        this.userRepository = userRepository;
    }

    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public CalledDTO findByIdDTO(String id) {
        Called called = findById(id);
        return new CalledDTO(called);
    }

    @Transactional
    public List<CalledDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(x -> new CalledDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional
    public Called save(CalledCreateDTO calledDTO) {
        return repository.save(newCalled(calledDTO));
    }

    @Transactional
    public Called update(String id, CalledCreateDTO calledCreateDTO){
        calledCreateDTO.setId(id);
        Called findCalled = findById(id);
        calledCreateDTO.setOpenDate(findCalled.getOpenDate());
        findCalled = newCalled(calledCreateDTO);
        return repository.save(findCalled);
    }

    private Called newCalled(CalledCreateDTO calledDTO) {
        Called called = new Called();
        Technician findTechnician = technicanService.findById(calledDTO.getTechnician());
        Client findClient = clientService.findById(calledDTO.getClient());
        if (calledDTO.getId() != null) {
            called.setId(calledDTO.getId());
            called.setOpenDate(calledDTO.getOpenDate());
        } else {
            called.setId(getUUid());
        }
        if(calledDTO.getStatus() == 2){
            called.setCloseDate(LocalDate.now());
        }
        called.setTitle(calledDTO.getTitle());
        called.setClient(findClient);
        called.setTechnician(findTechnician);
        called.setObservation(calledDTO.getObservation());
        called.setStatus(Status.toEnum(calledDTO.getStatus()));
        called.setPriority(Priority.toEnum(calledDTO.getPriority()));
        return called;
    }

    @Transactional
    public Called update(String id, CalledDTO dto) {
        dto.setId(id);
        Called oldCalled = findById(id);
        oldCalled = new Called(dto);
        return repository.save(oldCalled);
    }

    private Called findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado"));
    }
}
