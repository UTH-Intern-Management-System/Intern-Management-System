package com.project.training.controllers;

import com.project.training.dtos.InternTrainingDTO;
import com.project.training.dtos.InternTrainingResponseDTO;
import com.project.training.services.InternTrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/intern-trainings")
@RequiredArgsConstructor
public class InternTrainingController {

    private final InternTrainingService internTrainingService;

    @PostMapping
    public ResponseEntity<InternTrainingResponseDTO> assignIntern(@RequestBody InternTrainingDTO dto) {
        return ResponseEntity.ok(internTrainingService.assignIntern(dto));
    }

    @GetMapping
    public ResponseEntity<List<InternTrainingResponseDTO>> getAll() {
        return ResponseEntity.ok(internTrainingService.getAll());
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<InternTrainingResponseDTO>> getByIntern(@PathVariable Long internId) {
        return ResponseEntity.ok(internTrainingService.getByIntern(internId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternTrainingResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody InternTrainingDTO dto) {
        return ResponseEntity.ok(internTrainingService.updateStatus(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeIntern(@PathVariable Long id) {
        internTrainingService.removeIntern(id);
        return ResponseEntity.noContent().build();
    }
}

