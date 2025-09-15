package com.project.recruitment.repositories;

import com.project.recruitment.models.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequirementRepository extends JpaRepository<Requirement, Long> {
}
