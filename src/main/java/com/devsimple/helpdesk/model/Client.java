package com.devsimple.helpdesk.model;

import com.devsimple.helpdesk.model.enums.Profile;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends User {
    private static final long serialVersionUID = 1l;

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

    public List<Called> getCalleds() {
        return calleds;
    }

    public void setCalleds(List<Called> calleds) {
        this.calleds = calleds;
    }
}
