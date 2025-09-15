package com.project.training.responses;

import com.project.training.models.InternStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternResponse {
    private Long id;

    private Long userId;

    private String fullname;

    private String email;

    private String phone_number;

    private String address;
    private String sex;
    private String university;
    private String major;
    private Integer year;
    private Double gpa;
    private String skills;
    private String languages;
    private String notes;

    private String applied_position;

    private InternStatus status;
}
