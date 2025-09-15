package com.project.gateway.configs;

import com.project.gateway.filter.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public GatewayConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                // 🟢 RECRUITMENT SERVICE (port 8081)
                .route("internships-controller", r -> r.path("/api/v1/internships/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8081"))
                .route("intern-controller", r -> r.path("/api/v1/interns/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8081"))

                // 🟢 SCHEDULE SERVICE (port 8082)
                .route("daily-progress-controller", r -> r.path("/api/v1/daily-progress/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8082"))
                .route("interview-controller", r -> r.path("/api/v1/interviews/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8082"))
                .route("skill-assessment-controller", r -> r.path("/api/v1/skill-assessments/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8082"))

                // 🟢 TRAINING SERVICE (port 8083)
                .route("training-program-controller", r -> r.path("/api/v1/training-programs/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8083"))
                .route("intern-trainings-controller", r -> r.path("/api/v1/intern-trainings/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8083"))

                // 🟢 USER SERVICE (port 8080)
                .route("users-controller", r -> r.path("/api/v1/users/**")
                        .filters(f -> f.filter(jwtFilter))
                        .uri("http://localhost:8080"))

                .build();
    }
}
