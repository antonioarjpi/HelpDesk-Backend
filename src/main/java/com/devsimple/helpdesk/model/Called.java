package com.devsimple.helpdesk.model;

import com.devsimple.helpdesk.model.enums.Priority;
import com.devsimple.helpdesk.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Called implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    private String id;
    private String title;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openDate = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closeDate;
    private String observation;
    private Priority priority;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Called() {
    }

    public Called(String id, String title, String observation, Priority priority, Status status, Technician technician, Client client) {
        this.id = id;
        this.title = title;
        this.observation = observation;
        this.priority = priority;
        this.status = status;
        this.technician = technician;
        this.client = client;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getOpenDate() {
        return openDate;
    }

    public void setOpenDate(LocalDate openDate) {
        this.openDate = openDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Called)) return false;

        Called called = (Called) o;

        if (id != null ? !id.equals(called.id) : called.id != null) return false;
        if (title != null ? !title.equals(called.title) : called.title != null) return false;
        if (openDate != null ? !openDate.equals(called.openDate) : called.openDate != null) return false;
        if (closeDate != null ? !closeDate.equals(called.closeDate) : called.closeDate != null) return false;
        if (observation != null ? !observation.equals(called.observation) : called.observation != null) return false;
        if (priority != called.priority) return false;
        if (status != called.status) return false;
        if (technician != null ? !technician.equals(called.technician) : called.technician != null) return false;
        return client != null ? client.equals(called.client) : called.client == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (openDate != null ? openDate.hashCode() : 0);
        result = 31 * result + (closeDate != null ? closeDate.hashCode() : 0);
        result = 31 * result + (observation != null ? observation.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (technician != null ? technician.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }
}
