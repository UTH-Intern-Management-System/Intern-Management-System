package com.example.recruitment.application;

import com.example.recruitment.campaign.Campaign;
import com.example.recruitment.campaign.CampaignRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/recruitment/applications")
public class ApplicationController {
    private final ApplicationRepository repo;
    private final CampaignRepository campaignRepo;
    public ApplicationController(ApplicationRepository repo, CampaignRepository campaignRepo){
        this.repo=repo; this.campaignRepo=campaignRepo;
    }

    @GetMapping("/campaign/{campaignId}")
    public List<Application> byCampaign(@PathVariable Long campaignId){ return repo.findByCampaignId(campaignId); }

    @PostMapping
    public Application submit(@RequestParam Long campaignId, @RequestParam String candidateName,
                              @RequestParam String email, @RequestParam(required=false) String resumeUrl){
        Campaign c = campaignRepo.findById(campaignId).orElseThrow();
        Application a = Application.builder().campaign(c).candidateName(candidateName).email(email).resumeUrl(resumeUrl).build();
        return repo.save(a);
    }

    @PutMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status){
        Application a = repo.findById(id).orElseThrow();
        a.setStatus(status); return repo.save(a);
    }
}
