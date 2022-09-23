package com.devsimple.helpdesk.service;

import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.exceptions.ObjectNotFoundException;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.repository.TechnicianRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TechnicanService {

    private TechnicianRepository repository;

    public TechnicanService(TechnicianRepository repository) {
        this.repository = repository;
    }

    public static String getUUid(){
        return UUID.randomUUID().toString().replace("-", " ");
    }

    public TechnicianDTO findById(String id){
        Technician technician = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
        return new TechnicianDTO(technician);
    }

    public List<TechnicianDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(x -> new TechnicianDTO(x))
                .collect(Collectors.toList());
    }
}
