package com.project.training.repositories;

import com.project.training.models.TrainingProgram;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, Long> {
}
