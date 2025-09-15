package com.project.schedule.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.schedule.models.InternStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InternResponseDTO {
    private Long id;

    private Long userId;

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

    @JsonProperty("applied_position")
    private String appliedPosition;

    private InternStatus status;
}
