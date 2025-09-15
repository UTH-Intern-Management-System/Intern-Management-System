package com.project.futabuslines.repositories;

import com.project.futabuslines.models.InternshipProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<InternshipProgram, Long> {
    List<InternshipProgram> findByCreatedBy(Long createdBy);
}
