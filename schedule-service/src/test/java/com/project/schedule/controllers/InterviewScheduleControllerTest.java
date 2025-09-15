package com.project.schedule.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.schedule.dtos.CreateInterviewScheduleRequest;
import com.project.schedule.dtos.UpdateInterviewScheduleRequest;
import com.project.schedule.models.InterviewSchedule.InterviewType;
import com.project.schedule.models.InterviewSchedule.InterviewStatus;
import com.project.schedule.services.InterviewScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InterviewScheduleController.class)
class InterviewScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InterviewScheduleService interviewScheduleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createInterviewSchedule_ShouldReturnCreatedInterview() throws Exception {
        // Given
        CreateInterviewScheduleRequest request = new CreateInterviewScheduleRequest();
        request.setCandidateName("John Doe");
        request.setCandidateEmail("john.doe@example.com");
        request.setPosition("Software Engineer");
        request.setDepartment("Engineering");
        request.setInterviewType(InterviewType.TECHNICAL);
        request.setScheduledDate(LocalDate.now().plusDays(1));
        request.setScheduledTime(LocalTime.of(14, 0));
        request.setDuration(60);
        request.setLocation("Conference Room A");
        request.setInterviewerIds(Arrays.asList(1L, 2L));
        request.setNotes("Technical interview for backend position");
        request.setCreatedBy(1L);

        // When & Then
        mockMvc.perform(post("/api/v1/interview-schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interview schedule created successfully"));
    }

    @Test
    void getInterviewScheduleById_ShouldReturnInterview() throws Exception {
        // Given
        Long interviewId = 1L;

        // When & Then
        mockMvc.perform(get("/api/v1/interview-schedules/{id}", interviewId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interview schedule retrieved successfully"));
    }

    @Test
    void updateInterviewSchedule_ShouldReturnUpdatedInterview() throws Exception {
        // Given
        Long interviewId = 1L;
        UpdateInterviewScheduleRequest request = new UpdateInterviewScheduleRequest();
        request.setStatus(InterviewStatus.COMPLETED);
        request.setNotes("Interview completed successfully");

        // When & Then
        mockMvc.perform(put("/api/v1/interview-schedules/{id}", interviewId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interview schedule updated successfully"));
    }

    @Test
    void deleteInterviewSchedule_ShouldReturnSuccess() throws Exception {
        // Given
        Long interviewId = 1L;

        // When & Then
        mockMvc.perform(delete("/api/v1/interview-schedules/{id}", interviewId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interview schedule deleted successfully"));
    }

    @Test
    void getAllInterviewSchedules_ShouldReturnList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/interview-schedules"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interview schedules retrieved successfully"));
    }

    @Test
    void getUpcomingInterviews_ShouldReturnList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/interview-schedules/upcoming"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Upcoming interviews retrieved successfully"));
    }

    @Test
    void getTodayInterviews_ShouldReturnList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/interview-schedules/today"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Today's interviews retrieved successfully"));
    }

    @Test
    void getInterviewsByStatus_ShouldReturnList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/interview-schedules/status/SCHEDULED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interviews retrieved successfully"));
    }

    @Test
    void getInterviewsByDepartment_ShouldReturnList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/interview-schedules/department/Engineering"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interviews retrieved successfully"));
    }

    @Test
    void getInterviewsByInterviewer_ShouldReturnList() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/interview-schedules/interviewer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interviews retrieved successfully"));
    }

    @Test
    void updateInterviewStatus_ShouldReturnUpdatedInterview() throws Exception {
        // Given
        Long interviewId = 1L;
        String status = "COMPLETED";

        // When & Then
        mockMvc.perform(patch("/api/v1/interview-schedules/{id}/status", interviewId)
                .param("status", status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interview status updated successfully"));
    }

    @Test
    void getInterviewStatistics_ShouldReturnStatistics() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/v1/interview-schedules/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Statistics retrieved successfully"));
    }
}
