package com.project.schedule.repositories;

import com.project.schedule.models.InterviewSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewScheduleRepository extends JpaRepository<InterviewSchedule, Long> {
}
