package com.project.training.dtos;

import com.project.training.models.InternStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternTrainingDTO {
    private Long internId;
    private Long programId;
    private InternStatus status; // default PENDING
}