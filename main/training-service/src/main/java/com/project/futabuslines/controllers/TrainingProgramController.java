package com.project.futabuslines.controllers;

import com.project.futabuslines.dtos.TrainingProgramCreateDTO;
import com.project.futabuslines.dtos.TrainingProgramResponseDTO;
import com.project.futabuslines.services.TrainingProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/training-programs")
@RequiredArgsConstructor
public class TrainingProgramController {
    private final TrainingProgramService trainingProgramService;

    // Create
    @PostMapping
    public ResponseEntity<TrainingProgramResponseDTO> createProgram(@RequestBody TrainingProgramCreateDTO dto) {
        return ResponseEntity.ok(trainingProgramService.createProgram(dto));
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<TrainingProgramResponseDTO>> getAllPrograms() {
        return ResponseEntity.ok(trainingProgramService.getAllPrograms());
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<TrainingProgramResponseDTO> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingProgramService.getProgramById(id));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<TrainingProgramResponseDTO> updateProgram(
            @PathVariable Long id,
            @RequestBody TrainingProgramCreateDTO dto) {
        return ResponseEntity.ok(trainingProgramService.updateProgram(id, dto));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long id) {
        trainingProgramService.deleteProgram(id);
        return ResponseEntity.noContent().build();
    }
}
