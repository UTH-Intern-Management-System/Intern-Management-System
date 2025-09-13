package com.project.futabuslines.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private String skill;
    private int score; // 1-10 scale
    private String comments;

    private LocalDate assessmentDate;
}

