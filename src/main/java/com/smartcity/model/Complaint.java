package com.smartcity.model;

import java.sql.Date;

public class Complaint {

    private int complaintId;
    private int citizenId;
    private String category;
    private String title;
    private String description;
    private String location;
    private String status;
    private String priority;
    private String suggestion;
    private Date complaintDate;
    private String department;
    private String aiRecommendation;

    public Complaint() {
    }

    public Complaint(int complaintId, int citizenId, String category,
                     String title, String description,
                     String location, String status,
                     String priority, String suggestion,
                     Date complaintDate) {

        this.complaintId = complaintId;
        this.citizenId = citizenId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.location = location;
        this.status = status;
        this.priority = priority;
        this.suggestion = suggestion;
        this.complaintDate = complaintDate;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public Date getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(Date complaintDate) {
        this.complaintDate = complaintDate;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getAiRecommendation() {
        return aiRecommendation;
    }

    public void setAiRecommendation(String aiRecommendation) {
        this.aiRecommendation = aiRecommendation;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", citizenId=" + citizenId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", complaintDate=" + complaintDate +
                '}';
    }
}