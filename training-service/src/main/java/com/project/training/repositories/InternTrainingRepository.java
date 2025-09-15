package com.project.training.repositories;

import com.project.training.models.InternTraining;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternTrainingRepository extends JpaRepository<InternTraining, Long> {
    List<InternTraining> findByInternId(Long internId);
    List<InternTraining> findByProgramId(Long programId);
}
