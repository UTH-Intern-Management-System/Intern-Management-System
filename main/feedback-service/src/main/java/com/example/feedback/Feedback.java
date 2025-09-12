package com.example.feedback;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Feedback {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Align with frontend: internId (who submits), mentorId (target), rating, type/category, status, comment
    private Long internId;
    private Long mentorId;

    private String type; // e.g. "program" | "mentor"
    private String category; // e.g. "program_experience"

    private Double rating;

    @Column(length = 4000)
    private String comment;

    private String status; // e.g. "submitted", "reviewed"

    private Instant createdAt = Instant.now();
}
