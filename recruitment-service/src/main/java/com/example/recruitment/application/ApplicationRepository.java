package com.example.recruitment.application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCampaignId(Long campaignId);
}
