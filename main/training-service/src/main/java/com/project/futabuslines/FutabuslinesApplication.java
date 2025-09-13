package com.project.futabuslines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FutabuslinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutabuslinesApplication.class, args);
	}

}
