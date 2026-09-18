package com.smartcity.model;

public class Department {

    private int departmentId;
    private String departmentName;
    private String description;

    // Default constructor
    public Department() {
    }

    // Parameterized constructor
    public Department(int departmentId, String departmentName, String description) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.description = description;
    }

    // Getters and Setters

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Common method
    public void handleComplaint() {
        System.out.println("General department is handling the complaint.");
    }
}