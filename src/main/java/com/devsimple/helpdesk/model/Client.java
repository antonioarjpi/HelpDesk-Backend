package com.devsimple.helpdesk.model;

import com.devsimple.helpdesk.dto.ClientDTO;
import com.devsimple.helpdesk.model.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Client extends User {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Called> calleds = new ArrayList<>();

    public Client() {
        super();
        addProfile(Profile.CLIENT);
    }

    public Client(List<Called> calleds) {
        this.calleds = calleds;
    }

    public Client(String id, String name, String cpf, String email, String password, List<Called> calleds) {
        super(id, name, cpf, email, password);
        this.calleds = calleds;
        addProfile(Profile.CLIENT);
    }

    public Client(ClientDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.profiles = dto.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.dateCadastre = dto.getDateCadastre();
        addProfile(Profile.CLIENT);
    }

    public List<Called> getCalleds() {
        return calleds;
    }

    public void setCalleds(List<Called> calleds) {
        this.calleds = calleds;
    }
}
