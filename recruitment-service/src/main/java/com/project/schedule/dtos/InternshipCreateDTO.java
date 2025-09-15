package com.project.schedule.dtos;

import com.project.schedule.models.ProgramStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InternshipCreateDTO {
    private String title;
    private String department;
    private int positions;
    private ProgramStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long createdBy;
    private List<RequirementDTO> requirements;
    private List<BenefitDTO> benefits;
}
