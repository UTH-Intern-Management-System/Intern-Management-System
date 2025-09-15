package com.project.schedule.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "skill_assessments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long internId;
    private Long mentorId;
    private Long moduleId;
    private String skill;
    private Double score;
    private String comments;
    private String feedback;

    private LocalDateTime assessedAt = LocalDateTime.now();
}

