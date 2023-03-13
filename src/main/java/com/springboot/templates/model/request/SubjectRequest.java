package com.springboot.templates.model.request;

import jakarta.validation.constraints.NotBlank;

public class SubjectRequest {
    @NotBlank(message = "{subject name required}")
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
