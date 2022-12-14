package com.devsimple.helpdesk.controller;

import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.dto.TechnicianUpdateDTO;
import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.service.TechnicanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/technical")
public class TechnicianController {

    private TechnicanService service;

    public TechnicianController(TechnicanService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(service.findByIdDTO(id));
    }

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Technician> save(@Valid @RequestBody TechnicianDTO technicianDTO) {
        return ResponseEntity.ok(service.save(technicianDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Technician> update(@PathVariable String id, @Valid @RequestBody TechnicianUpdateDTO dto){
        return ResponseEntity.ok().body(service.update(id, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
