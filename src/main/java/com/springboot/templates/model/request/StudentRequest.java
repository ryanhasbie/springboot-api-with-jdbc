package com.springboot.templates.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentRequest {
    @NotBlank(message = "{invalid.firstname.required}")
    @Size(min = 3, max = 15, message = "{invalid.firstname.size}")
    private String firstName;
    @NotBlank(message = "{invalid.lastname.required}")
    @Size(min = 3, max = 15, message = "{invalid.lastname.size}")
    private String lastName;
    @NotBlank(message = "{invalid.email.required}")
    @Size(min = 10, message = "{invalid.email.size}")
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
