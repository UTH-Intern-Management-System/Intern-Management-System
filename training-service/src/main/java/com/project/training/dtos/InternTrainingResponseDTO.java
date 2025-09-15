package com.project.training.dtos;

import com.project.training.models.InternStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternTrainingResponseDTO {
    private Long id;
    private Long internId;
    private Long programId;
    private String programTitle;
    private InternStatus status;
}