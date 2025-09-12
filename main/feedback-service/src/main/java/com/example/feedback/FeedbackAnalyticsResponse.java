package com.example.feedback;
import lombok.Getter; import lombok.Setter;
import java.util.Map;

@Getter @Setter
public class FeedbackAnalyticsResponse {
    private int totalFeedback;
    private double averageRating;
    private Map<String, Long> feedbackByType;
    private Map<String, Long> ratingDistribution;
}



