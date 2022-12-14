package com.devsimple.helpdesk.dto;

import com.devsimple.helpdesk.model.Client;
import com.devsimple.helpdesk.model.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    private String id;
    @NotBlank(message = "O campo nome é obrigatório")
    private String name;
    @CPF(message = "CPF inválido")
    private String cpf;
    @Email(message = "e-mail inválido")
    private String email;
    private Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCadastre = LocalDate.now();

    public ClientUpdateDTO() {
    }

    public ClientUpdateDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.email = client.getEmail();
        this.profiles = client.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.dateCadastre = client.getDateCadastre();
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
