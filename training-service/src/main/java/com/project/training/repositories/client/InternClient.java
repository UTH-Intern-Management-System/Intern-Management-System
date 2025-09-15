package com.project.training.repositories.client;

import com.project.training.configs.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "intern-service",
        url = "http://localhost:8088/api/v1/interns",
        configuration = FeignClientConfig.class
)
public interface InternClient {

    @GetMapping("/{id}")
    void getInternById(@PathVariable("id") Long id);
}
