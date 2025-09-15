package com.project.schedule.services;

import com.project.schedule.dtos.InterviewScheduleRequestDTO;
import com.project.schedule.dtos.InterviewScheduleResponseDTO;
import com.project.schedule.models.InterviewSchedule;
import com.project.schedule.repositories.InterviewScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewScheduleService {
    private final InterviewScheduleRepository repository;

    public InterviewScheduleResponseDTO create(InterviewScheduleRequestDTO dto) {
        InterviewSchedule schedule = InterviewSchedule.builder()
                .candidateName(dto.getCandidateName())
                .candidateEmail(dto.getCandidateEmail())
                .jobPosition(dto.getJobPosition())
                .department(dto.getDepartment())
                .interviewType(dto.getInterviewType())
                .durationMinutes(dto.getDurationMinutes())
                .interviewDate(dto.getInterviewDate())
                .interviewTime(dto.getInterviewTime())
                .location(dto.getLocation())
                .notes(dto.getNotes())
                .build();

        InterviewSchedule saved = repository.save(schedule);
        return toResponseDTO(saved);
    }

    public List<InterviewScheduleResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public InterviewScheduleResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(this::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public InterviewScheduleResponseDTO update(Long id, InterviewScheduleRequestDTO dto) {
        InterviewSchedule existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        existing.setCandidateName(dto.getCandidateName());
        existing.setCandidateEmail(dto.getCandidateEmail());
        existing.setJobPosition(dto.getJobPosition());
        existing.setDepartment(dto.getDepartment());
        existing.setInterviewType(dto.getInterviewType());
        existing.setDurationMinutes(dto.getDurationMinutes());
        existing.setInterviewDate(dto.getInterviewDate());
        existing.setInterviewTime(dto.getInterviewTime());
        existing.setLocation(dto.getLocation());
        existing.setNotes(dto.getNotes());

        return toResponseDTO(repository.save(existing));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private InterviewScheduleResponseDTO toResponseDTO(InterviewSchedule schedule) {
        return InterviewScheduleResponseDTO.builder()
                .id(schedule.getId())
                .candidateName(schedule.getCandidateName())
                .candidateEmail(schedule.getCandidateEmail())
                .jobPosition(schedule.getJobPosition())
                .department(schedule.getDepartment())
                .interviewType(schedule.getInterviewType())
                .durationMinutes(schedule.getDurationMinutes())
                .interviewDate(schedule.getInterviewDate())
                .interviewTime(schedule.getInterviewTime())
                .location(schedule.getLocation())
                .notes(schedule.getNotes())
                .build();
    }

    public long countInterview(){
        return repository.count();
    }
}

