package com.devsimple.helpdesk.controller;

import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.service.TechnicanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technical")
public class TechnicianController {

    private TechnicanService service;

    public TechnicianController(TechnicanService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
