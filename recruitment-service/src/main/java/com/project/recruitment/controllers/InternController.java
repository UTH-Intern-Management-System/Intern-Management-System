package com.project.recruitment.controllers;

import com.project.recruitment.dtos.InternCreateDTO;
import com.project.recruitment.dtos.InternResponseDTO;
import com.project.recruitment.models.InternStatus;
import com.project.recruitment.services.InternService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/interns")
@RequiredArgsConstructor
public class InternController {
    private final InternService internService;

    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestBody InternCreateDTO dto) {
        try {
            InternResponseDTO response = internService.applyIntern(dto);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", 400, "message", e.getMessage())
            );
        }
    }
    @GetMapping("get-all")
    public ResponseEntity<List<InternResponseDTO>> getAll() {
        return ResponseEntity.ok(internService.getAllInterns());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(internService.getInternById(id));
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", 400, "message", e.getMessage())
            );
        }

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @RequestParam InternStatus status
    ) {
        try {
            return ResponseEntity.ok(internService.updateInternStatus(id, status));
        }catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(
                        Map.of("status", 400, "message", e.getMessage())
                );
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateIntern(
            @PathVariable Long id,
            @RequestBody InternCreateDTO dto
    ){
        try {
            return ResponseEntity.ok(internService.updateIntern(id, dto));
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                Map.of("status", 400, "message", e.getMessage())
        );
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteIntern(@PathVariable Long id) {
        try {
            internService.deleteIntern(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", 400, "message", e.getMessage())
            );
        }
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> getTotalInterns() {
        long totalInterns = internService.countInterns();
        return ResponseEntity.ok(Map.of("total_interns", totalInterns));
    }
}

