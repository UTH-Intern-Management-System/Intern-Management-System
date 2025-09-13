package com.project.futabuslines.controllers;

import com.project.futabuslines.dtos.InternshipCreateDTO;
import com.project.futabuslines.dtos.InternshipResponseDTO;
import com.project.futabuslines.services.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/recruitment/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final InternshipService internshipService;

    @GetMapping
    public ResponseEntity<List<InternshipResponseDTO>> getAllCampaigns() {
        return ResponseEntity.ok(internshipService.getAllInternships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCampaignById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(internshipService.getInternshipDetail(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", 400, "message", e.getMessage())
            );
        }
    }

    @PostMapping
    public ResponseEntity<?> createCampaign(@RequestBody InternshipCreateDTO dto) {
        try {
            InternshipResponseDTO response = internshipService.createInternship(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", 400, "message", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCampaign(
            @PathVariable Long id,
            @RequestBody InternshipCreateDTO dto
    ) {
        try {
            return ResponseEntity.ok(internshipService.updateInternship(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", 400, "message", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long id) {
        try {
            internshipService.deleteInternship(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", 400, "message", e.getMessage())
            );
        }
    }

    @GetMapping("/analytics")
    public ResponseEntity<?> getRecruitmentAnalytics() {
        try {
            List<InternshipResponseDTO> campaigns = internshipService.getAllInternships();
            
            int totalCampaigns = campaigns.size();
            int activeCampaigns = (int) campaigns.stream()
                    .filter(c -> c.getStatus().toString().equals("ACTIVE"))
                    .count();
            int totalApplications = campaigns.stream()
                    .mapToInt(InternshipResponseDTO::getApplications)
                    .sum();
            int totalPositions = campaigns.stream()
                    .mapToInt(InternshipResponseDTO::getPositions)
                    .sum();
            
            double applicationToPositionRatio = totalPositions > 0 ? 
                    (double) totalApplications / totalPositions : 0.0;
            
            Map<String, Object> analytics = Map.of(
                    "totalCampaigns", totalCampaigns,
                    "activeCampaigns", activeCampaigns,
                    "totalApplications", totalApplications,
                    "totalPositions", totalPositions,
                    "applicationToPositionRatio", String.format("%.2f", applicationToPositionRatio)
            );
            
            return ResponseEntity.ok(analytics);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    Map.of("status", 400, "message", e.getMessage())
            );
        }
    }
}
