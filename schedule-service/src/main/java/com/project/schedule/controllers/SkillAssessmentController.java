package com.project.schedule.controllers;

import com.project.schedule.dtos.SkillAssessmentCreateDTO;
import com.project.schedule.dtos.SkillAssessmentResponseDTO;
import com.project.schedule.services.SkillAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/skill-assessments")
@RequiredArgsConstructor
public class SkillAssessmentController {

    private final SkillAssessmentService skillAssessmentService;

    // Tạo đánh giá
    @PostMapping
    public ResponseEntity<SkillAssessmentResponseDTO> createAssessment(@RequestBody SkillAssessmentCreateDTO dto) {
        return ResponseEntity.ok(skillAssessmentService.createAssessment(dto));
    }

    // Lấy tất cả
    @GetMapping
    public ResponseEntity<List<SkillAssessmentResponseDTO>> getAll() {
        return ResponseEntity.ok(skillAssessmentService.getAll());
    }

    // Lấy theo internTrainingId
    @GetMapping("/intern/{internTrainingId}")
    public ResponseEntity<List<SkillAssessmentResponseDTO>> getByIntern(@PathVariable Long internTrainingId) {
        return ResponseEntity.ok(skillAssessmentService.getByIntern(internTrainingId));
    }

    // Lấy theo moduleId
    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<SkillAssessmentResponseDTO>> getByModule(@PathVariable Long moduleId) {
        return ResponseEntity.ok(skillAssessmentService.getByModule(moduleId));
    }

    // Lấy theo mentorId
    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<SkillAssessmentResponseDTO>> getByMentor(@PathVariable Long mentorId) {
        return ResponseEntity.ok(skillAssessmentService.getByMentor(mentorId));
    }
}
