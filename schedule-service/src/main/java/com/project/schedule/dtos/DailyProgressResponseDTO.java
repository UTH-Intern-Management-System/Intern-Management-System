package com.project.schedule.dtos;

import com.project.schedule.models.ProgressStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyProgressResponseDTO {
    private Long id;
    private Long internId;
    private Long mentorId;
    private LocalDate date;
    private String activities;
    private String feedback;
    private ProgressStatus status;
}
