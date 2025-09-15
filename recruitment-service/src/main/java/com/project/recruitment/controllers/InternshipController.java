package com.project.recruitment.controllers;

import com.project.recruitment.dtos.InternshipCreateDTO;
import com.project.recruitment.dtos.InternshipResponseDTO;
import com.project.recruitment.services.InternshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/internships")
@RequiredArgsConstructor
public class InternshipController {
    private final InternshipService internshipService;


    @PostMapping("create")
    public ResponseEntity<?> createInternship(
            @Valid @RequestBody InternshipCreateDTO dto,
            BindingResult result
    ){
        try {
            if (result.hasErrors()) {
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            InternshipResponseDTO response = internshipService.createInternship(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("get/{id}")
    public ResponseEntity<InternshipResponseDTO> getInternship(@PathVariable Long id) {
        return ResponseEntity.ok(internshipService.getInternshipDetail(id));
    }
    @GetMapping("get-all")
    public ResponseEntity<List<InternshipResponseDTO>> getAll() {
        return ResponseEntity.ok(internshipService.getAllInternships());
    }

    @GetMapping("/created-by/{userId}")
    public ResponseEntity<List<InternshipResponseDTO>> getByCreatedBy(@PathVariable Long userId) {
        return ResponseEntity.ok(internshipService.getByCreatedBy(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InternshipResponseDTO> update(
            @PathVariable Long id,
            @RequestBody InternshipCreateDTO dto
    ) {
        return ResponseEntity.ok(internshipService.updateInternship(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        internshipService.deleteInternship(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Long>> getTotalUsers() {
        long totalUsers = internshipService.countRecruitment();
        return ResponseEntity.ok(Map.of("total_recruitment", totalUsers));
    }
}
