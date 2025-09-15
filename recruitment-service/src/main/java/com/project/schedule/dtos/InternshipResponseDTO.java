package com.project.schedule.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.schedule.models.ProgramStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class InternshipResponseDTO {
    private Long id;

    private String title;

    private String department;

    private int positions;

    private int applications;

    private ProgramStatus status;
    @JsonProperty("start_date")

    private LocalDate startDate;
    @JsonProperty("end_date")

    private LocalDate endDate;

    @JsonProperty("created_by")
    private Long createdBy;
    // dữ liệu user từ UserService
    @JsonProperty("created_by_name")
    private String createdByName;
    @JsonProperty("created_by_email")
    private String createdByEmail;

    private List<String> requirements;
    private List<String> benefits;
}
