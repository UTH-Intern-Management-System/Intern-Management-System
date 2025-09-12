package com.example.assessment.models;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class Assessment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long internId;
    private Long mentorId;
    @ManyToOne(optional=false) private Kpi kpi;
    private int score;
    @Column(length=2000) private String comment;
    private Instant assessedAt = Instant.now();
    
    // New fields for detailed assessment
    private Integer technicalScore;
    private Integer problemSolvingScore;
    private Integer communicationScore;
    private Integer teamworkScore;
    private Integer learningScore;
    @Column(length=2000) private String feedback;
    @Column(length=2000) private String goals;

    // Constructors
    public Assessment() {}

    public Assessment(Long id, Long internId, Long mentorId, Kpi kpi, int score, String comment, Instant assessedAt) {
        this.id = id;
        this.internId = internId;
        this.mentorId = mentorId;
        this.kpi = kpi;
        this.score = score;
        this.comment = comment;
        this.assessedAt = assessedAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInternId() { return internId; }
    public void setInternId(Long internId) { this.internId = internId; }

    public Long getMentorId() { return mentorId; }
    public void setMentorId(Long mentorId) { this.mentorId = mentorId; }

    public Kpi getKpi() { return kpi; }
    public void setKpi(Kpi kpi) { this.kpi = kpi; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Instant getAssessedAt() { return assessedAt; }
    public void setAssessedAt(Instant assessedAt) { this.assessedAt = assessedAt; }
    
    // New getters and setters
    public Integer getTechnicalScore() { return technicalScore; }
    public void setTechnicalScore(Integer technicalScore) { this.technicalScore = technicalScore; }
    
    public Integer getProblemSolvingScore() { return problemSolvingScore; }
    public void setProblemSolvingScore(Integer problemSolvingScore) { this.problemSolvingScore = problemSolvingScore; }
    
    public Integer getCommunicationScore() { return communicationScore; }
    public void setCommunicationScore(Integer communicationScore) { this.communicationScore = communicationScore; }
    
    public Integer getTeamworkScore() { return teamworkScore; }
    public void setTeamworkScore(Integer teamworkScore) { this.teamworkScore = teamworkScore; }
    
    public Integer getLearningScore() { return learningScore; }
    public void setLearningScore(Integer learningScore) { this.learningScore = learningScore; }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    
    public String getGoals() { return goals; }
    public void setGoals(String goals) { this.goals = goals; }
}
