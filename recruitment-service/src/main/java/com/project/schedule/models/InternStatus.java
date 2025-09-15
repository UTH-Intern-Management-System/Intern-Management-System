package com.project.schedule.models;

public enum InternStatus {
    PENDING,       // Mặc định khi apply
    APPROVED,      // Được phê duyệt
    IN_PROGRESS,   // Đang thực tập
    COMPLETED,     // Hoàn thành
    REJECTED,      // Từ chối
    TERMINATED     // Kết thúc do không đạt
}
