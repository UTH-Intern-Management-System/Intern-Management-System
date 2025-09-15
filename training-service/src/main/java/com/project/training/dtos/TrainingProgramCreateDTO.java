package com.project.training.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class TrainingProgramCreateDTO {
    private String title;
    private String description;
    private String duration;
    private int maxParticipants;
    private Long coordinatorId;
    private List<Long> mentors;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TrainingModuleDTO> modules;
}
