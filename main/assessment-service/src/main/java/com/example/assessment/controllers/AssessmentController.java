package com.example.assessment.controllers;
import com.example.assessment.repositories.AssessmentRepository;
import com.example.assessment.repositories.KpiRepository;
import com.example.assessment.models.Assessment;
import com.example.assessment.models.Kpi;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/assessment")
public class AssessmentController {
    private final AssessmentRepository assessRepo;
    private final KpiRepository kpiRepo;
    public AssessmentController(AssessmentRepository assessRepo, KpiRepository kpiRepo){
        this.assessRepo=assessRepo; this.kpiRepo=kpiRepo;
    }

    @PostMapping("/kpis") public Kpi createKpi(@RequestParam String name, @RequestParam String description){
        return kpiRepo.save(Kpi.builder().name(name).description(description).build());
    }

    @PostMapping("/assessments")
    public Assessment assess(@RequestParam Long internId, @RequestParam Long mentorId,
                             @RequestParam Long kpiId, @RequestParam int score,
                             @RequestParam(required=false) String comment){
        Kpi k = kpiRepo.findById(kpiId).orElseThrow();
        Assessment a = Assessment.builder().internId(internId).mentorId(mentorId).kpi(k)
                .score(score).comment(comment).build();
        return assessRepo.save(a);
    }

    @GetMapping("/assessments/intern/{internId}")
    public List<Assessment> byIntern(@PathVariable Long internId){ return assessRepo.findByInternId(internId); }
}
