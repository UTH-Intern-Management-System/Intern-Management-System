package com.project.recruitment.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InternCreateDTO {
    @JsonProperty("fullname")
    private String fullName;
    private String email;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    private String sex;

    private String university;
    private String major;
    private Integer year;
    private Double gpa;

    private String skills;
    private String languages;

    private String notes;
    private String appliedPosition;
}
