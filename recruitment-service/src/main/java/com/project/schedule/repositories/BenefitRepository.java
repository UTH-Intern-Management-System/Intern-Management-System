package com.project.schedule.repositories;

import com.project.schedule.models.Benefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BenefitRepository extends JpaRepository<Benefit, Long> {
}

