package com.example.feedback;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
public class FeedbackCreateRequest {
    private Long internId;
    private Long mentorId;
    private String type;
    private String category;
    private Double rating;
    private String comment;
}



