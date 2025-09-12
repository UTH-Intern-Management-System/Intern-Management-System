package com.project.futabuslines.repositories.client;

import com.project.futabuslines.configs.FeignClientConfig;
import com.project.futabuslines.dtos.UserCreateRequestDTO;
import com.project.futabuslines.responses.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "user-service",
        url = "http://localhost:8080/api/v1/users",
        configuration = FeignClientConfig.class
)
public interface UserClient {
    @GetMapping("get-info/{id}")
    UserResponse getUserById(@PathVariable("id") Long id);

    @PostMapping("/register")
    UserResponse createInternUser(@RequestBody UserCreateRequestDTO request);

    @DeleteMapping("/delete/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
