package com.project.schedule.dtos;

import com.project.schedule.models.ProgressStatus;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class    DailyProgressCreateDTO {
    private Long internId;
    private Long mentorId;
    private LocalDate date;
    private String activities;
    private String feedback;
    private ProgressStatus status;
}

