package com.example.recruitment.application;

import com.example.recruitment.campaign.Campaign;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) private Campaign campaign;
    private String candidateName;
    private String email;
    private String resumeUrl;
    @Enumerated(EnumType.STRING) private ApplicationStatus status = ApplicationStatus.SUBMITTED;
    private Instant submittedAt = Instant.now();
}
