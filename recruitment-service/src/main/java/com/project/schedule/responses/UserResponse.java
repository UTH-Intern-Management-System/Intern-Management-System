package com.project.schedule.responses;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String fullname;
    private String email;
    private String phone_number;
    private String address;
    private String sex;
}