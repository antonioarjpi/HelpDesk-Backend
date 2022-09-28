package com.devsimple.helpdesk.dto;

import com.devsimple.helpdesk.model.Called;

import java.io.Serializable;
import java.time.LocalDate;

public class CalledCreateDTO implements Serializable {
    private static final long serialVersionUID = 1l;

    private String id;
    private String title;
    private LocalDate openDate = LocalDate.now();
    private LocalDate closeDate;
    private String observation;
    private Integer priority;
    private Integer status;
    private String technician;
    private String client;

    public CalledCreateDTO() {
        super();
    }

    public CalledCreateDTO(Called called) {
        super();
        this.id = called.getId();
        this.title = called.getTitle();
        this.openDate = called.getOpenDate();
        this.closeDate = called.getCloseDate();
        this.observation = called.getObservation();
        this.priority = called.getPriority().getCode();
        this.status = called.getStatus().getCode();
        this.technician = called.getTechnician().getId();
        this.client = called.getClient().getId();
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
