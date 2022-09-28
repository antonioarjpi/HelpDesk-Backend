package com.devsimple.helpdesk.controller;

import com.devsimple.helpdesk.dto.CalledCreateDTO;
import com.devsimple.helpdesk.dto.CalledDTO;
import com.devsimple.helpdesk.model.Called;
import com.devsimple.helpdesk.service.CalledService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/calleds")
public class CalledController {

    private CalledService service;

    public CalledController(CalledService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CalledDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CalledDTO> findById(@PathVariable String id){
        return ResponseEntity.ok(service.findByIdDTO(id));
    }

    @PostMapping
    public ResponseEntity<CalledDTO> save(@RequestBody CalledCreateDTO dto){
        Called called = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(called.getId()).toUri();
        return ResponseEntity.created(uri).body(new CalledDTO(called));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalledDTO> update(@PathVariable String id, @RequestBody CalledCreateDTO dto){
        Called called = service.update(id, dto);
        return ResponseEntity.ok(new CalledDTO(called));
    }
}
