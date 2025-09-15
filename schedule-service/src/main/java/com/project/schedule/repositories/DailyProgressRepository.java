package com.project.schedule.repositories;

import com.project.schedule.models.DailyProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyProgressRepository extends JpaRepository<DailyProgress, Long> {
    List<DailyProgress> findByInternId(Long internId);
    List<DailyProgress> findByMentorId(Long mentorId);
}
