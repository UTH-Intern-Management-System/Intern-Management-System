package com.project.training.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training_programs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String duration; // ví dụ: "8 weeks"
    private int maxParticipants;
    private int currentParticipants;

    @Enumerated(EnumType.STRING)
    private ProgramStatus status; // ACTIVE, CLOSED

    private LocalDate startDate;
    private LocalDate endDate;
    private Long coordinatorId;

    @ElementCollection
    @CollectionTable(name = "program_mentors", joinColumns = @JoinColumn(name = "program_id"))
    @Column(name = "mentor_id")
    private List<Long> mentors = new ArrayList<>();

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TrainingModule> modules = new ArrayList<>();
}

