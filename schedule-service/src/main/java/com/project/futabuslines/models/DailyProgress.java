package com.project.futabuslines.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "daily_progress")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long internId;
    private Long mentorId;
    private LocalDate date;

    private String activities;
    private String feedback;

    @Enumerated(EnumType.STRING)
    private ProgressStatus status;

    private LocalDateTime createdAt = LocalDateTime.now();
}



