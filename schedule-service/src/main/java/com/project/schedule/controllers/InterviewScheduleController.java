package com.project.schedule.controllers;

import com.project.schedule.dtos.InterviewScheduleRequestDTO;
import com.project.schedule.dtos.InterviewScheduleResponseDTO;
import com.project.schedule.services.InterviewScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/interviews")
@RequiredArgsConstructor
public class InterviewScheduleController {

    private final InterviewScheduleService service;

    @PostMapping("create")
    public ResponseEntity<InterviewScheduleResponseDTO> create(
            @Valid @RequestBody InterviewScheduleRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));

    }

    @GetMapping("get-all")
    public ResponseEntity<List<InterviewScheduleResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<InterviewScheduleResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<InterviewScheduleResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody InterviewScheduleRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> getTotalInterviews() {
        long totalInterviews = service.countInterview();
        return ResponseEntity.ok(Map.of("total_interviews", totalInterviews));
    }
}

