package com.example.recruitment.interview;

import com.example.recruitment.application.Application;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class Interview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) private Application application;
    private LocalDateTime scheduledAt;
    private String location;
    @Enumerated(EnumType.STRING) private InterviewStatus status = InterviewStatus.SCHEDULED;
    @Column(length=2000) private String notes;
}
