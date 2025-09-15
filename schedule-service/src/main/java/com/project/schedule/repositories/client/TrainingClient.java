package com.project.schedule.repositories.client;

import com.project.schedule.responses.TrainingModuleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "training-service",
        url = "http://localhost:8081/api/v1/training-modules"
)
public interface TrainingClient {
    @GetMapping("/{id}")
    TrainingModuleResponse getModuleById(@PathVariable("id") Long id);
}

