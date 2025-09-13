package com.project.futabuslines.repositories;

import com.project.futabuslines.models.InternTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternTrainingRepository extends JpaRepository<InternTraining, Long> {
    List<InternTraining> findByInternId(Long internId);
    List<InternTraining> findByProgramId(Long programId);
}
