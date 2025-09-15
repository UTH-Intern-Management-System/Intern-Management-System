package com.project.training.dtos;

import lombok.Data;
import java.util.List;

@Data
public class TrainingModuleDTO {
    private String title;
    private String duration;
    private List<String> topics;
}