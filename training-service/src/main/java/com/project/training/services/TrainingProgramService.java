package com.project.training.services;

import com.project.training.dtos.TrainingModuleDTO;
import com.project.training.dtos.TrainingProgramCreateDTO;
import com.project.training.dtos.TrainingProgramResponseDTO;
import com.project.training.models.ProgramStatus;
import com.project.training.models.TrainingModule;
import com.project.training.models.TrainingProgram;
import com.project.training.repositories.TrainingProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingProgramService {
    private final TrainingProgramRepository trainingProgramRepository;

    // Create
    public TrainingProgramResponseDTO createProgram(TrainingProgramCreateDTO dto) {
        TrainingProgram program = TrainingProgram.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .maxParticipants(dto.getMaxParticipants())
                .currentParticipants(0)
                .status(ProgramStatus.ACTIVE) // mặc định ACTIVE
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .coordinatorId(dto.getCoordinatorId())
                .mentors(dto.getMentors())
                .build();

        if (dto.getModules() != null) {
            dto.getModules().forEach(m -> {
                TrainingModule module = TrainingModule.builder()
                        .title(m.getTitle())
                        .duration(m.getDuration())
                        .topics(m.getTopics())
                        .program(program)
                        .build();
                program.getModules().add(module);
            });
        }

        TrainingProgram saved = trainingProgramRepository.save(program);
        return mapToResponse(saved);
    }

    // Get all
    public List<TrainingProgramResponseDTO> getAllPrograms() {
        return trainingProgramRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get by id
    public TrainingProgramResponseDTO getProgramById(Long id) {
        TrainingProgram program = trainingProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));
        return mapToResponse(program);
    }

    // Update
    public TrainingProgramResponseDTO updateProgram(Long id, TrainingProgramCreateDTO dto) {
        TrainingProgram program = trainingProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        program.setTitle(dto.getTitle());
        program.setDescription(dto.getDescription());
        program.setDuration(dto.getDuration());
        program.setMaxParticipants(dto.getMaxParticipants());

        program.setStartDate(dto.getStartDate());
        program.setEndDate(dto.getEndDate());

        program.getModules().clear();
        if (dto.getModules() != null) {
            dto.getModules().forEach(m -> {
                program.getModules().add(
                        TrainingModule.builder()
                                .title(m.getTitle())
                                .duration(m.getDuration())
                                .topics(m.getTopics())
                                .program(program)
                                .build()
                );
            });
        }

        TrainingProgram updated = trainingProgramRepository.save(program);
        return mapToResponse(updated);
    }

    // Delete
    public void deleteProgram(Long id) {
        TrainingProgram program = trainingProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));
        trainingProgramRepository.delete(program);
    }

    // Helper: map entity -> DTO
    private TrainingProgramResponseDTO mapToResponse(TrainingProgram program) {
        return TrainingProgramResponseDTO.builder()
                .id(program.getId())
                .title(program.getTitle())
                .description(program.getDescription())
                .duration(program.getDuration())
                .maxParticipants(program.getMaxParticipants())
                .currentParticipants(program.getCurrentParticipants())
                .status(program.getStatus().name())
                .startDate(program.getStartDate())
                .endDate(program.getEndDate())
                .coordinatorId(program.getCoordinatorId())
                .mentors(program.getMentors())
                .modules(program.getModules().stream().map(m -> {
                    TrainingModuleDTO dto = new TrainingModuleDTO();
                    dto.setTitle(m.getTitle());
                    dto.setDuration(m.getDuration());
                    dto.setTopics(m.getTopics());
                    return dto;
                }).collect(Collectors.toList()))
                .build();
    }
    public long countTraining() {
        return trainingProgramRepository.count();
    }
}
