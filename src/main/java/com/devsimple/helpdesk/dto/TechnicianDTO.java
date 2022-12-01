package com.devsimple.helpdesk.dto;

import com.devsimple.helpdesk.model.Technician;
import com.devsimple.helpdesk.model.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class TechnicianDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private String id;

    @NotBlank(message = "O campo nome é obrigatório")
    private String name;

    @CPF(message = "CPF inválido")
    private String cpf;

    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "O campo senha é obrigatório")
    @Size(min = 4, max = 16, message = "Senha precisa ter 4 a 16 caracteres")
    private String password;

    private Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCadastre = LocalDate.now();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
