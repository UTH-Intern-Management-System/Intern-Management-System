package com.example.feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByToUserId(Long userId);
    List<Feedback> findByFromUserId(Long userId);
}
