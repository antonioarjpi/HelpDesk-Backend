package com.devsimple.helpdesk.dto;

import com.devsimple.helpdesk.model.Called;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalledDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private String id;
    private String title;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closeDate;
    private String observation;
    private Integer priority;
    private Integer status;
    private String technician;
    private String technicianName;
    private String technicianEmail;
    private String client;
    private String clientName;
    private String clientEmail;

    public CalledDTO() {
        super();
    }

    public CalledDTO(Called called) {
        super();
        this.id = called.getId();
        this.title = called.getTitle();
        this.openDate = called.getOpenDate();
        this.closeDate = called.getCloseDate();
        this.observation = called.getObservation();
        this.priority = called.getPriority().getCode();
        this.status = called.getStatus().getCode();
        this.technician = called.getTechnician().getId();
        this.technicianName = called.getTechnician().getName();
        this.technicianEmail = called.getTechnician().getEmail();
        this.client = called.getClient().getId();
        this.clientName = called.getClient().getName();
        this.clientEmail = called.getClient().getEmail();
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getTechnicianEmail() {
        return technicianEmail;
    }

    public void setTechnicianEmail(String technicianEmail) {
        this.technicianEmail = technicianEmail;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
