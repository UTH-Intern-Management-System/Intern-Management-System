package com.project.futabuslines.controllers;

import com.project.futabuslines.dtos.InternshipCreateDTO;
import com.project.futabuslines.dtos.InternshipResponseDTO;
import com.project.futabuslines.services.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/internships")
@RequiredArgsConstructor
public class InternshipController {
    private final InternshipService internshipService;


    @PostMapping
    public ResponseEntity<InternshipResponseDTO> createInternship(@RequestBody InternshipCreateDTO dto) {
        InternshipResponseDTO response = internshipService.createInternship(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipResponseDTO> getInternship(@PathVariable Long id) {
        return ResponseEntity.ok(internshipService.getInternshipDetail(id));
    }
    @GetMapping
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
}
