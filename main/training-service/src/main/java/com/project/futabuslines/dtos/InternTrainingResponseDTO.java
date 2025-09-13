package com.project.futabuslines.dtos;

import com.project.futabuslines.models.InternStatus;
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