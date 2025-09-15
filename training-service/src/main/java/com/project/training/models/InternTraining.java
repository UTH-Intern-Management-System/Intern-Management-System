package com.project.training.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "intern_training")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long internId;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram program;

    @Enumerated(EnumType.STRING)
    private InternStatus status;
}

