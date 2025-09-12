package com.example.assessment.dto;

public class AssessmentRequest {
    private Long internId;
    private Long mentorId;
    private String skillName;
    private Integer technicalScore;
    private Integer problemSolvingScore;
    private Integer communicationScore;
    private Integer teamworkScore;
    private Integer learningScore;
    private String feedback;
    private String goals;
    private String assessedAt;

    // Constructors
    public AssessmentRequest() {}

    public AssessmentRequest(Long internId, Long mentorId, String skillName, 
                           Integer technicalScore, Integer problemSolvingScore, 
                           Integer communicationScore, Integer teamworkScore, 
                           Integer learningScore, String feedback, String goals, String assessedAt) {
        this.internId = internId;
        this.mentorId = mentorId;
        this.skillName = skillName;
        this.technicalScore = technicalScore;
        this.problemSolvingScore = problemSolvingScore;
        this.communicationScore = communicationScore;
        this.teamworkScore = teamworkScore;
        this.learningScore = learningScore;
        this.feedback = feedback;
        this.goals = goals;
        this.assessedAt = assessedAt;
    }

    // Getters and Setters
    public Long getInternId() {
        return internId;
    }

    public void setInternId(Long internId) {
        this.internId = internId;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(Integer technicalScore) {
        this.technicalScore = technicalScore;
    }

    public Integer getProblemSolvingScore() {
        return problemSolvingScore;
    }

    public void setProblemSolvingScore(Integer problemSolvingScore) {
        this.problemSolvingScore = problemSolvingScore;
    }

    public Integer getCommunicationScore() {
        return communicationScore;
    }

    public void setCommunicationScore(Integer communicationScore) {
        this.communicationScore = communicationScore;
    }

    public Integer getTeamworkScore() {
        return teamworkScore;
    }

    public void setTeamworkScore(Integer teamworkScore) {
        this.teamworkScore = teamworkScore;
    }

    public Integer getLearningScore() {
        return learningScore;
    }

    public void setLearningScore(Integer learningScore) {
        this.learningScore = learningScore;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getAssessedAt() {
        return assessedAt;
    }

    public void setAssessedAt(String assessedAt) {
        this.assessedAt = assessedAt;
    }
}
