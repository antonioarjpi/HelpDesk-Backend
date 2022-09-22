package com.devsimple.helpdesk.model;

import com.devsimple.helpdesk.model.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_user")
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    protected String id;
    protected String name;

    @Column(unique = true)
    protected String cpf;

    @Column(unique = true)
    protected String email;
    protected String password;

    @ElementCollection(fetch = FetchType.EAGER) //Coleção de elementos do tipo integer
    @CollectionTable(name = "profiles")
    protected Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDate dateCadastre = LocalDate.now();

    public User() {
        super();
        addProfile(Profile.CLIENT);
    }

    public User(String id, String name, String cpf, String email, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        addProfile(Profile.CLIENT);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (cpf != null ? !cpf.equals(user.cpf) : user.cpf != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (profiles != null ? !profiles.equals(user.profiles) : user.profiles != null) return false;
        return dateCadastre != null ? dateCadastre.equals(user.dateCadastre) : user.dateCadastre == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cpf != null ? cpf.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (profiles != null ? profiles.hashCode() : 0);
        result = 31 * result + (dateCadastre != null ? dateCadastre.hashCode() : 0);
        return result;
    }
}
