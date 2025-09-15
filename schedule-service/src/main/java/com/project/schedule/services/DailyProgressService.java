package com.project.schedule.services;

import com.project.schedule.dtos.DailyProgressCreateDTO;
import com.project.schedule.dtos.DailyProgressResponseDTO;
import com.project.schedule.models.DailyProgress;
import com.project.schedule.repositories.DailyProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyProgressService {

    private final DailyProgressRepository progressRepository;

    public DailyProgressResponseDTO createProgress(DailyProgressCreateDTO dto) {
        DailyProgress progress = DailyProgress.builder()
                .internId(dto.getInternId())
                .mentorId(dto.getMentorId())
                .date(dto.getDate())
                .activities(dto.getActivities())
                .feedback(dto.getFeedback())
                .status(dto.getStatus())
                .build();

        DailyProgress saved = progressRepository.save(progress);
        return mapToResponse(saved);
    }

    public List<DailyProgressResponseDTO> getAll() {
        return progressRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public List<DailyProgressResponseDTO> getByIntern(Long internId) {
        return progressRepository.findByInternId(internId).stream().map(this::mapToResponse).toList();
    }

    public List<DailyProgressResponseDTO> getByMentor(Long mentorId) {
        return progressRepository.findByMentorId(mentorId).stream().map(this::mapToResponse).toList();
    }

    public DailyProgressResponseDTO updateProgress(Long id, DailyProgressCreateDTO dto) {
        DailyProgress progress = progressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Progress not found"));

        progress.setDate(dto.getDate());
        progress.setActivities(dto.getActivities());
        progress.setFeedback(dto.getFeedback());
        progress.setStatus(dto.getStatus());

        return mapToResponse(progressRepository.save(progress));
    }

    public void deleteProgress(Long id) {
        progressRepository.deleteById(id);
    }

    private DailyProgressResponseDTO mapToResponse(DailyProgress progress) {
        return DailyProgressResponseDTO.builder()
                .id(progress.getId())
                .internId(progress.getInternId())
                .mentorId(progress.getMentorId())
                .date(progress.getDate())
                .activities(progress.getActivities())
                .feedback(progress.getFeedback())
                .status(progress.getStatus())
                .build();
    }
}
