package com.project.training.controllers;

import com.project.training.dtos.TrainingProgramCreateDTO;
import com.project.training.dtos.TrainingProgramResponseDTO;
import com.project.training.services.TrainingProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/training-programs")
@RequiredArgsConstructor
public class TrainingProgramController {
    private final TrainingProgramService trainingProgramService;

    // Create
    @PostMapping("create")
    public ResponseEntity<TrainingProgramResponseDTO> createProgram(@RequestBody TrainingProgramCreateDTO dto) {
        return ResponseEntity.ok(trainingProgramService.createProgram(dto));
    }

    // Get all
    @GetMapping("get-all")
    public ResponseEntity<List<TrainingProgramResponseDTO>> getAllPrograms() {
        return ResponseEntity.ok(trainingProgramService.getAllPrograms());
    }

    // Get by id
    @GetMapping("get/{id}")
    public ResponseEntity<TrainingProgramResponseDTO> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingProgramService.getProgramById(id));
    }

    // Update
    @PutMapping("update/{id}")
    public ResponseEntity<TrainingProgramResponseDTO> updateProgram(
            @PathVariable Long id,
            @RequestBody TrainingProgramCreateDTO dto) {
        return ResponseEntity.ok(trainingProgramService.updateProgram(id, dto));
    }

    // Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        trainingProgramService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
    // GET: http://localhost:8080/api/v1/users/total
    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> getTotalTraining() {
        long totalUsers = trainingProgramService.countTraining();
        return ResponseEntity.ok(Map.of("total_trainings", totalUsers));
    }
}
