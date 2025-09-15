package com.project.schedule.repositories;

import com.project.schedule.models.SkillAssessment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillAssessmentRepository extends JpaRepository<SkillAssessment, Long> {
    List<SkillAssessment> findByInternId(Long internTrainingId);
    List<SkillAssessment> findByModuleId(Long moduleId);
    List<SkillAssessment> findByMentorId(Long mentorId);
}
