package com.project.schedule.controllers;

import com.project.schedule.dtos.DailyProgressCreateDTO;
import com.project.schedule.dtos.DailyProgressResponseDTO;
import com.project.schedule.services.DailyProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/daily-progress")
@RequiredArgsConstructor
public class DailyProgressController {

    private final DailyProgressService dailyProgressService;

    @PostMapping
    public ResponseEntity<DailyProgressResponseDTO> create(@RequestBody DailyProgressCreateDTO dto) {
        return ResponseEntity.ok(dailyProgressService.createProgress(dto));
    }

    @GetMapping
    public ResponseEntity<List<DailyProgressResponseDTO>> getAll() {
        return ResponseEntity.ok(dailyProgressService.getAll());
    }

    @GetMapping("/intern/{internId}")
    public ResponseEntity<List<DailyProgressResponseDTO>> getByIntern(@PathVariable Long internId) {
        return ResponseEntity.ok(dailyProgressService.getByIntern(internId));
    }

    @GetMapping("/mentor/{mentorId}")
    public ResponseEntity<List<DailyProgressResponseDTO>> getByMentor(@PathVariable Long mentorId) {
        return ResponseEntity.ok(dailyProgressService.getByMentor(mentorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyProgressResponseDTO> update(@PathVariable Long id, @RequestBody DailyProgressCreateDTO dto) {
        return ResponseEntity.ok(dailyProgressService.updateProgress(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dailyProgressService.deleteProgress(id);
        return ResponseEntity.noContent().build();
    }
}
