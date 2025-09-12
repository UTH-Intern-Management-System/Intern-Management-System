package com.example.recruitment.interview;

import com.example.recruitment.application.Application;
import com.example.recruitment.application.ApplicationRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/recruitment/interviews")
public class InterviewController {
    private final InterviewRepository repo;
    private final ApplicationRepository appRepo;
    public InterviewController(InterviewRepository repo, ApplicationRepository appRepo){
        this.repo=repo; this.appRepo=appRepo;
    }

    @GetMapping("/application/{appId}")
    public List<Interview> byApp(@PathVariable Long appId){ return repo.findByApplicationId(appId); }

    @PostMapping
    public Interview schedule(@RequestParam Long applicationId, @RequestParam String location,
                              @RequestParam String scheduledAtISO){
        Application app = appRepo.findById(applicationId).orElseThrow();
        Interview i = Interview.builder().application(app).location(location)
                .scheduledAt(LocalDateTime.parse(scheduledAtISO)).build();
        return repo.save(i);
    }

    @PutMapping("/{id}/status")
    public Interview updateStatus(@PathVariable Long id, @RequestParam InterviewStatus status){
        Interview i = repo.findById(id).orElseThrow(); i.setStatus(status); return repo.save(i);
    }
}
