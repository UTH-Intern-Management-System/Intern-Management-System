package com.example.assessment.models;
import jakarta.persistence.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Kpi {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length=2000) private String description;
}
