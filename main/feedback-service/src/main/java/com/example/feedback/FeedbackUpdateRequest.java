package com.example.feedback;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
public class FeedbackUpdateRequest {
    private String type;
    private String category;
    private Double rating;
    private String comment;
    private String status;
}



