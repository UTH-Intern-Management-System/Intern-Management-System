package com.project.schedule.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillAssessmentResponseDTO {
    private Long id;
    private Long internTrainingId;
    private Long moduleId;
    private String moduleTitle;
    private Long mentorId;
    private Double score;
    private String feedback;
    private LocalDateTime assessedAt;
}
