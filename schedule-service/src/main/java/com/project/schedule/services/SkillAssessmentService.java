package com.project.schedule.services;

import com.project.schedule.dtos.SkillAssessmentCreateDTO;
import com.project.schedule.dtos.SkillAssessmentResponseDTO;
import com.project.schedule.models.SkillAssessment;
import com.project.schedule.repositories.SkillAssessmentRepository;
import com.project.schedule.repositories.client.TrainingClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillAssessmentService {

    private final SkillAssessmentRepository assessmentRepository;
    private final TrainingClient trainingClient;

    // Tạo đánh giá
    public SkillAssessmentResponseDTO createAssessment(SkillAssessmentCreateDTO dto) {
        SkillAssessment assessment = SkillAssessment.builder()
                .internId(dto.getInternId())
                .moduleId(dto.getModuleId())
                .mentorId(dto.getMentorId())
                .score(dto.getScore())
                .feedback(dto.getFeedback())
                .build();

        SkillAssessment saved = assessmentRepository.save(assessment);
        return mapToResponse(saved);
    }

    // Lấy tất cả
    public List<SkillAssessmentResponseDTO> getAll() {
        return assessmentRepository.findAll()
                .stream().map(this::mapToResponse).toList();
    }

    // Lấy theo intern
    public List<SkillAssessmentResponseDTO> getByIntern(Long internTrainingId) {
        return assessmentRepository.findByInternId(internTrainingId)
                .stream().map(this::mapToResponse).toList();
    }

    // Lấy theo module
    public List<SkillAssessmentResponseDTO> getByModule(Long moduleId) {
        return assessmentRepository.findByModuleId(moduleId)
                .stream().map(this::mapToResponse).toList();
    }

    // Lấy theo mentor
    public List<SkillAssessmentResponseDTO> getByMentor(Long mentorId) {
        return assessmentRepository.findByMentorId(mentorId)
                .stream().map(this::mapToResponse).toList();
    }

    private SkillAssessmentResponseDTO mapToResponse(SkillAssessment a) {
        String moduleTitle;
        try {
            var module = trainingClient.getModuleById(a.getModuleId());
            moduleTitle = module.getTitle();
        } catch (Exception e) {
            moduleTitle = "Unknown Module";
        }

        return SkillAssessmentResponseDTO.builder()
                .id(a.getId())
                .internTrainingId(a.getInternId())
                .moduleId(a.getModuleId())
                .moduleTitle(moduleTitle)
                .mentorId(a.getMentorId())
                .score(a.getScore())
                .feedback(a.getFeedback())
                .assessedAt(a.getAssessedAt())
                .build();
    }
}
