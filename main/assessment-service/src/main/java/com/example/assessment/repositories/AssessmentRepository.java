package com.example.assessment.repositories;
import com.example.assessment.models.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> findByInternId(Long internId);
}
