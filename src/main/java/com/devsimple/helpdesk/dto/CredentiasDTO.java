package com.devsimple.helpdesk.dto;

public class CredentiasDTO {

    private String email;
    private String password;

    public CredentiasDTO() {
    }

    public CredentiasDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
}
