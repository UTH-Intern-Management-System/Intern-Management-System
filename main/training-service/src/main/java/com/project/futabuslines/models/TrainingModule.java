package com.project.futabuslines.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "training_modules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String duration;

    @ElementCollection
    @CollectionTable(name = "module_topics", joinColumns = @JoinColumn(name = "module_id"))
    @Column(name = "topic")
    private List<String> topics = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "program_id")
    private TrainingProgram program;
}
