package com.project.schedule.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillAssessmentCreateDTO {
    private Long internId;
    private Long moduleId;
    private Long mentorId;
    private Double score;
    private String feedback;
}
