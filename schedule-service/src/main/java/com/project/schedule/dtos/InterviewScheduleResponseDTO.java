package com.project.schedule.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class InterviewScheduleResponseDTO {
    private Long id;
    private String candidateName;
    private String candidateEmail;
    private String jobPosition;
    private String department;
    private String interviewType;
    private Integer durationMinutes;
    private LocalDate interviewDate;
    private LocalTime interviewTime;
    private String location;
    private String notes;
}
