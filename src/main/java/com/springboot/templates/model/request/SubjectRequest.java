package com.springboot.templates.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SubjectRequest {
    @NotBlank(message = "{invalid.subject.required}")
    @Size(min = 5, max = 10, message = "{invalid.subject.size}")
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
