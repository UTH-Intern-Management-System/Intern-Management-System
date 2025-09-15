package com.project.training.services;

import com.project.training.dtos.InternTrainingDTO;
import com.project.training.dtos.InternTrainingResponseDTO;
import com.project.training.models.InternTraining;
import com.project.training.models.TrainingProgram;
import com.project.training.repositories.InternTrainingRepository;
import com.project.training.repositories.TrainingProgramRepository;
import com.project.training.repositories.client.InternClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternTrainingService {

    private final InternTrainingRepository internTrainingRepository;
    private final TrainingProgramRepository trainingProgramRepository;
    private final InternClient internClient;


    // Create
    public InternTrainingResponseDTO assignIntern(InternTrainingDTO dto) {
        // Check intern tồn tại bên Intern Service
        try {
            internClient.getInternById(dto.getInternId()); // nếu không tồn tại sẽ ném FeignException
        } catch (feign.FeignException.BadRequest e) {
            throw new RuntimeException("Intern không tồn tại: " + e.contentUTF8());
        } catch (feign.FeignException e) {
            throw new RuntimeException("Lỗi khi gọi InternService: " + e.getMessage());
        }

        // Check chương trình
        TrainingProgram program = trainingProgramRepository.findById(dto.getProgramId())
                .orElseThrow(() -> new RuntimeException("Program not found"));

        InternTraining internTraining = InternTraining.builder()
                .internId(dto.getInternId())
                .program(program)
                .status(dto.getStatus())
                .build();

        InternTraining saved = internTrainingRepository.save(internTraining);
        return mapToResponse(saved);
    }

    // Read all
    public List<InternTrainingResponseDTO> getAll() {
        return internTrainingRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Read by intern
    public List<InternTrainingResponseDTO> getByIntern(Long internId) {
        return internTrainingRepository.findByInternId(internId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Update status
    public InternTrainingResponseDTO updateStatus(Long id, InternTrainingDTO dto) {
        InternTraining internTraining = internTrainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InternTraining not found"));

        internTraining.setStatus(dto.getStatus());
        InternTraining updated = internTrainingRepository.save(internTraining);

        return mapToResponse(updated);
    }

    // Delete
    public void removeIntern(Long id) {
        internTrainingRepository.deleteById(id);
    }

    private InternTrainingResponseDTO mapToResponse(InternTraining internTraining) {
        return InternTrainingResponseDTO.builder()
                .id(internTraining.getId())
                .internId(internTraining.getInternId())
                .programId(internTraining.getProgram().getId())
                .programTitle(internTraining.getProgram().getTitle())
                .status(internTraining.getStatus())
                .build();
    }
}
