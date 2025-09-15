package com.project.schedule.dtos;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class InterviewScheduleRequestDTO {

    @NotBlank(message = "Candidate name is required")
    private String candidateName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String candidateEmail;

    @NotBlank(message = "Job position is required")
    private String jobPosition;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Interview type is required")
    private String interviewType;

    @NotNull(message = "Duration is required")
    private Integer durationMinutes;

    @NotNull(message = "Interview date is required")
    private LocalDate interviewDate;

    @NotNull(message = "Interview time is required")
    private LocalTime interviewTime;

    @NotBlank(message = "Location is required")
    private String location;

    private String notes;
}