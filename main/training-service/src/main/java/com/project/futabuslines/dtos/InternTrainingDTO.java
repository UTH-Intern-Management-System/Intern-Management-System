package com.project.futabuslines.dtos;

import com.project.futabuslines.models.InternStatus;
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