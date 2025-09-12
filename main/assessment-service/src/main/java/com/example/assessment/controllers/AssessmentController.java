package com.example.assessment.controllers;
import com.example.assessment.repositories.AssessmentRepository;
import com.example.assessment.repositories.KpiRepository;
import com.example.assessment.models.Assessment;
import com.example.assessment.models.Kpi;
import com.example.assessment.dto.AssessmentRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController @RequestMapping("/assessment")
public class AssessmentController {
    private static final Logger logger = LoggerFactory.getLogger(AssessmentController.class);
    private final AssessmentRepository assessRepo;
    private final KpiRepository kpiRepo;
    public AssessmentController(AssessmentRepository assessRepo, KpiRepository kpiRepo){
        this.assessRepo=assessRepo; this.kpiRepo=kpiRepo;
    }

    @GetMapping("/kpis") 
    public List<Kpi> getAllKpis(){
        return kpiRepo.findAll();
    }

    @PostMapping("/assessments")
    @Transactional
    public Assessment assess(@RequestBody AssessmentRequest request){
        logger.info("Creating assessment for internId: {}, mentorId: {}, skillName: {}", 
                   request.getInternId(), request.getMentorId(), request.getSkillName());
        try {
            // Tìm hoặc tạo KPI mới
            Kpi k = kpiRepo.findByName(request.getSkillName())
                .orElseGet(() -> {
                    Kpi newKpi = new Kpi();
                    newKpi.setName(request.getSkillName());
                    newKpi.setDescription("Skill assessment for " + request.getSkillName());
                    newKpi.setWeight(20);
                    newKpi.setCategory("Technical");
                    return kpiRepo.save(newKpi);
                });
            
            Assessment a = new Assessment();
            a.setInternId(request.getInternId());
            a.setMentorId(request.getMentorId() != null ? request.getMentorId() : 7L); // Default mentor
            a.setKpi(k);
            a.setTechnicalScore(request.getTechnicalScore());
            a.setProblemSolvingScore(request.getProblemSolvingScore());
            a.setCommunicationScore(request.getCommunicationScore());
            a.setTeamworkScore(request.getTeamworkScore());
            a.setLearningScore(request.getLearningScore());
            a.setFeedback(request.getFeedback());
            a.setGoals(request.getGoals());
            a.setAssessedAt(java.time.Instant.now());
            
            Assessment saved = assessRepo.save(a);
            logger.info("Assessment created successfully with ID: {}", saved.getId());
            return saved;
        } catch (Exception e) {
            logger.error("Error creating assessment: ", e);
            throw e;
        }
    }


    @GetMapping("/assessments")
    public List<Assessment> getAllAssessments(){
        return assessRepo.findAll();
    }

}
