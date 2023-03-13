package com.springboot.templates.model.request;

import jakarta.validation.constraints.NotBlank;

public class StudentRequest {
    @NotBlank(message = "{firstname required}")
    private String firstName;
    @NotBlank(message = "{lastname required}")
    private String lastName;
    @NotBlank(message = "{email required}")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
