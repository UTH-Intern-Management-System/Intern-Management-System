package com.project.futabuslines.services;


import com.project.futabuslines.dtos.InternshipCreateDTO;
import com.project.futabuslines.dtos.InternshipResponseDTO;
import com.project.futabuslines.models.Benefit;
import com.project.futabuslines.models.InternshipProgram;
import com.project.futabuslines.models.Requirement;
import com.project.futabuslines.repositories.InternshipRepository;
import com.project.futabuslines.repositories.client.UserClient;
import com.project.futabuslines.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipService {
    private final InternshipRepository internshipRepository;
    private final UserClient userClient;

    public InternshipResponseDTO createInternship(InternshipCreateDTO dto) {
        InternshipProgram program = InternshipProgram.builder()
                .title(dto.getTitle())
                .department(dto.getDepartment())
                .positions(dto.getPositions())
                .applications(0)
                .status(dto.getStatus())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .createdBy(dto.getCreatedBy())
                .createdAt(LocalDate.now().atStartOfDay())
                .build();

        // Map requirements
        if (dto.getRequirements() != null) {
            dto.getRequirements().forEach(reqDto -> {
                Requirement req = Requirement.builder()
                        .description(reqDto.getDescription())
                        .program(program) // gán quan hệ
                        .build();
                program.getRequirements().add(req);
            });
        }

        // Map benefits
        if (dto.getBenefits() != null) {
            dto.getBenefits().forEach(benDto -> {
                Benefit ben = Benefit.builder()
                        .description(benDto.getDescription())
                        .program(program)
                        .build();
                program.getBenefits().add(ben);
            });
        }

        InternshipProgram saved = internshipRepository.save(program);

        // gọi sang UserService để enrich dữ liệu
        UserResponse user = userClient.getUserById(saved.getCreatedBy());

        return InternshipResponseDTO.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .department(saved.getDepartment())
                .positions(saved.getPositions())
                .applications(saved.getApplications())
                .status(saved.getStatus())
                .startDate(saved.getStartDate())
                .endDate(saved.getEndDate())
                .createdBy(saved.getCreatedBy())
                .createdByName(user.getFullname())
                .createdByEmail(user.getEmail())
                .requirements(
                        saved.getRequirements().stream()
                                .map(Requirement::getDescription)
                                .toList()
                )
                .benefits(
                        saved.getBenefits().stream()
                                .map(Benefit::getDescription)
                                .toList()
                )
                .build();
    }

    public InternshipResponseDTO getInternshipDetail(Long id) {
        InternshipProgram program = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        // gọi UserService để lấy thông tin user
        UserResponse user = userClient.getUserById(program.getCreatedBy());

        return InternshipResponseDTO.builder()
                .id(program.getId())
                .title(program.getTitle())
                .department(program.getDepartment())
                .positions(program.getPositions())
                .applications(program.getApplications())
                .status(program.getStatus())
                .startDate(program.getStartDate())
                .endDate(program.getEndDate())
                .createdBy(program.getCreatedBy())
                .createdByName(user.getFullname())
                .createdByEmail(user.getEmail())
                .requirements(
                        program.getRequirements().stream()
                                .map(Requirement::getDescription)
                                .toList()
                )
                .benefits(
                        program.getBenefits().stream()
                                .map(Benefit::getDescription)
                                .toList()
                )
                .build();
    }
    // Get all internships
    public List<InternshipResponseDTO> getAllInternships() {
        return internshipRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get internships by createdBy
    public List<InternshipResponseDTO> getByCreatedBy(Long userId) {
        return internshipRepository.findByCreatedBy(userId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Update internship
    public InternshipResponseDTO updateInternship(Long id, InternshipCreateDTO dto) {
        InternshipProgram program = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));

        program.setTitle(dto.getTitle());
        program.setDepartment(dto.getDepartment());
        program.setPositions(dto.getPositions());
        program.setStatus(dto.getStatus());
        program.setStartDate(dto.getStartDate());
        program.setEndDate(dto.getEndDate());

        // clear & replace requirements
        program.getRequirements().clear();
        if (dto.getRequirements() != null) {
            dto.getRequirements().forEach(r -> {
                Requirement req = Requirement.builder()
                        .description(r.getDescription())
                        .program(program)
                        .build();
                program.getRequirements().add(req);
            });
        }

        // clear & replace benefits
        program.getBenefits().clear();
        if (dto.getBenefits() != null) {
            dto.getBenefits().forEach(b -> {
                Benefit ben = Benefit.builder()
                        .description(b.getDescription())
                        .program(program)
                        .build();
                program.getBenefits().add(ben);
            });
        }

        InternshipProgram updated = internshipRepository.save(program);
        return mapToResponse(updated);
    }

    // Delete internship
    public void deleteInternship(Long id) {
        InternshipProgram program = internshipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found"));
        internshipRepository.delete(program);
    }

    // Helper: mapping entity -> response
    private InternshipResponseDTO mapToResponse(InternshipProgram program) {
        UserResponse user = userClient.getUserById(program.getCreatedBy());

        return InternshipResponseDTO.builder()
                .id(program.getId())
                .title(program.getTitle())
                .department(program.getDepartment())
                .positions(program.getPositions())
                .applications(program.getApplications())
                .status(program.getStatus())
                .startDate(program.getStartDate())
                .endDate(program.getEndDate())
                .createdBy(program.getCreatedBy())
                .createdByName(user.getFullname())
                .createdByEmail(user.getEmail())
                .requirements(
                        program.getRequirements().stream()
                                .map(Requirement::getDescription)
                                .toList()
                )
                .benefits(
                        program.getBenefits().stream()
                                .map(Benefit::getDescription)
                                .toList()
                )
                .build();
    }
}
