package com.project.training.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class TrainingProgramResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String duration;
    private int maxParticipants;
    private int currentParticipants;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long coordinatorId;
    private List<Long> mentors;
    private List<TrainingModuleDTO> modules;
}
