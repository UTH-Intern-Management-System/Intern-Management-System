package com.example.recruitment.campaign;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

record CampaignCreateRequest(@NotBlank String title, String description, String startDateISO, String endDateISO) {}

@RestController
@RequestMapping("/recruitment/campaigns")
public class CampaignController {
    private final CampaignRepository repo;
    public CampaignController(CampaignRepository repo) { this.repo = repo; }

    @GetMapping public List<Campaign> all(){ return repo.findAll(); }

    @PostMapping public Campaign create(@RequestBody CampaignCreateRequest req){
        Campaign c = Campaign.builder()
                .title(req.title()).description(req.description())
                .startDate(req.startDateISO()==null?null: LocalDate.parse(req.startDateISO()))
                .endDate(req.endDateISO()==null?null: LocalDate.parse(req.endDateISO()))
                .status(CampaignStatus.OPEN).build();
        return repo.save(c);
    }
}
