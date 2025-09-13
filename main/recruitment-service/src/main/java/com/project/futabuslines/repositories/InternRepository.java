package com.project.futabuslines.repositories;

import com.project.futabuslines.models.Intern;
import com.project.futabuslines.models.InternStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long> {
    List<Intern> findByStatus(InternStatus status);
    List<Intern> findByAppliedPosition(String appliedPosition);
}

