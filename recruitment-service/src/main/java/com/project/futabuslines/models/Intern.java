package com.project.futabuslines.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Intern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // userId được tạo từ UserService
    @Column(nullable = false)
    private Long userId;

    // Thông tin học vấn
    private String university;
    private String major;
    private Integer year;
    private Double gpa;

    // Kỹ năng và ngôn ngữ: lưu đơn giản chuỗi JSON hoặc CSV
    private String skills;   // ví dụ: "Java, C++, Spring Boot"
    private String languages; // ví dụ: "IELTS 8.0, TOEIC 900"

    // Ghi chú
    @Column(length = 1000)
    private String notes;

    // Vị trí ứng tuyển
    private String appliedPosition;

    // Trạng thái: PENDING, ACCEPTED, REJECTED, ONBOARD
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InternStatus status;
}
