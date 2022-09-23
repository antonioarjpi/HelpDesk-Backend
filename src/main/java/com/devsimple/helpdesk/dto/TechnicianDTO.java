package com.devsimple.helpdesk.dto;

import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class TechnicianDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    protected String id;
    protected String name;
    protected String cpf;
    protected String email;
    protected Set<Integer> profiles = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dateCadastre = LocalDate.now();

    public TechnicianDTO() {
    }

    public TechnicianDTO(Technician technician) {
        this.id = technician.getId();
        this.name = technician.getName();
        this.cpf = technician.getCpf();
        this.email = technician.getEmail();
        this.profiles = technician.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.dateCadastre = technician.getDateCadastre();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile.getCode());
    }

    public LocalDate getDateCadastre() {
        return dateCadastre;
    }

    public void setDateCadastre(LocalDate dateCadastre) {
        this.dateCadastre = dateCadastre;
    }
}
