package com.devsimple.helpdesk.model;

import com.devsimple.helpdesk.dto.TechnicianDTO;
import com.devsimple.helpdesk.dto.TechnicianUpdateDTO;
import com.devsimple.helpdesk.model.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Technician extends User {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Called> calleds = new ArrayList<>();

    public Technician() {
        super();
        addProfile(Profile.TECH);
    }

    public Technician(List<Called> calleds) {
        this.calleds = calleds;
    }

    public Technician(String id, String name, String cpf, String email, String password, List<Called> calleds) {
        super(id, name, cpf, email, password);
        this.calleds = calleds;
        addProfile(Profile.TECH);
    }

    public Technician(TechnicianDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.profiles = dto.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.dateCadastre = dto.getDateCadastre();
        addProfile(Profile.TECH);
    }

    public Technician(TechnicianUpdateDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.profiles = dto.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.dateCadastre = dto.getDateCadastre();
        addProfile(Profile.TECH);
    }

    public List<Called> getCalleds() {
        return calleds;
    }

    public void setCalleds(List<Called> calleds) {
        this.calleds = calleds;
    }
}
