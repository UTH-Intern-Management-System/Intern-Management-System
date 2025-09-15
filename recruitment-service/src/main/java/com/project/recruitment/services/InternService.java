package com.project.recruitment.services;

import com.project.recruitment.dtos.InternCreateDTO;
import com.project.recruitment.dtos.InternResponseDTO;
import com.project.recruitment.dtos.UserCreateRequestDTO;
import com.project.recruitment.models.Intern;
import com.project.recruitment.models.InternStatus;
import com.project.recruitment.repositories.InternRepository;
import com.project.recruitment.repositories.client.UserClient;
import com.project.recruitment.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternService {
    private final InternRepository internRepository;
    private final UserClient userClient;

    public InternResponseDTO applyIntern(InternCreateDTO dto) {
        try {
            // 1. Gọi UserService để tạo user account
            UserCreateRequestDTO userReq = UserCreateRequestDTO.builder()
                    .fullName(dto.getFullName())
                    .email(dto.getEmail())
                    .phoneNumber(dto.getPhoneNumber())
                    .address(dto.getAddress())
                    .sex(dto.getSex())
                    .password("Inter123456@")
                    .retypePassword("Inter123456@")
                    .role(2L) // role INTERN
                    .build();

            UserResponse user = userClient.createInternUser(userReq);

            // 2. Lưu Intern vào DB
            Intern intern = Intern.builder()
                    .userId(user.getId())
                    .university(dto.getUniversity())
                    .major(dto.getMajor())
                    .year(dto.getYear())
                    .gpa(dto.getGpa())
                    .skills(dto.getSkills())
                    .languages(dto.getLanguages())
                    .notes(dto.getNotes())
                    .appliedPosition(dto.getAppliedPosition())
                    .status(InternStatus.PENDING) // mặc định chờ duyệt
                    .build();

            Intern saved = internRepository.save(intern);

            // 3. Trả về response
            return InternResponseDTO.builder()
                    .id(saved.getId())
                    .userId(user.getId())
                    .fullName(user.getFullname())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhone_number())
                    .address(user.getAddress())
                    .sex(user.getSex())
                    .university(saved.getUniversity())
                    .major(saved.getMajor())
                    .year(saved.getYear())
                    .gpa(saved.getGpa())
                    .skills(saved.getSkills())
                    .languages(saved.getLanguages())
                    .notes(saved.getNotes())
                    .appliedPosition(saved.getAppliedPosition())
                    .status(saved.getStatus())
                    .build();

        } catch (feign.FeignException.BadRequest e) {
            // lấy message chi tiết từ UserService (vd: "Email đã tồn tại")
            String msg = e.contentUTF8();
            throw new RuntimeException("Đăng ký User thất bại: " + msg);
        } catch (feign.FeignException e) {
            throw new RuntimeException("Lỗi khi gọi UserService: " + e.getMessage());
        }
    }
    public List<InternResponseDTO> getAllInterns() {
        return internRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public InternResponseDTO getInternById(Long id) {
        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intern not found"));
        return mapToDTO(intern);
    }

    public InternResponseDTO updateInternStatus(Long id, InternStatus newStatus) {
        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        intern.setStatus(newStatus);

        Intern updated = internRepository.save(intern);
        return mapToDTO(updated);
    }

    public InternResponseDTO updateIntern(Long id, InternCreateDTO dto) {
        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        intern.setUniversity(dto.getUniversity());
        intern.setMajor(dto.getMajor());
        intern.setYear(dto.getYear());
        intern.setGpa(dto.getGpa());
        intern.setSkills(dto.getSkills());
        intern.setLanguages(dto.getLanguages());
        intern.setNotes(dto.getNotes());
        intern.setAppliedPosition(dto.getAppliedPosition());

        Intern updated = internRepository.save(intern);
        return mapToDTO(updated);
    }

    public void deleteIntern(Long id) {
        Intern intern = internRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intern not found"));

        // Xóa cả user bên UserService
        userClient.deleteUser(intern.getUserId());
        internRepository.delete(intern);
    }

    private InternResponseDTO mapToDTO(Intern intern) {
        UserResponse user = userClient.getUserById(intern.getUserId());

        return InternResponseDTO.builder()
                .id(intern.getId())
                .userId(user.getId())
                .fullName(user.getFullname())
                .email(user.getEmail())
                .phoneNumber(user.getPhone_number())
                .address(user.getAddress())
                .sex(user.getSex())
                .university(intern.getUniversity())
                .major(intern.getMajor())
                .year(intern.getYear())
                .gpa(intern.getGpa())
                .skills(intern.getSkills())
                .languages(intern.getLanguages())
                .notes(intern.getNotes())
                .appliedPosition(intern.getAppliedPosition())
                .status(intern.getStatus())
                .build();
    }

    public long countInterns() {
        return internRepository.count();
    }
}



