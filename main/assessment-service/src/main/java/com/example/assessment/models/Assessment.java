package com.example.assessment.models;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Assessment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long internId;
    private Long mentorId;
    @ManyToOne(optional=false) private Kpi kpi;
    private int score;
    @Column(length=2000) private String comment;
    private Instant assessedAt = Instant.now();
}
