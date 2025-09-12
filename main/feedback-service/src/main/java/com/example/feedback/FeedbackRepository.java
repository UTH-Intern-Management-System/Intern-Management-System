package com.example.feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByInternId(Long internId);
    List<Feedback> findByMentorId(Long mentorId);
    List<Feedback> findByType(String type);
}
