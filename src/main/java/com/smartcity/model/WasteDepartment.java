package com.smartcity.model;

public class WasteDepartment extends Department implements ComplaintHandler {

    public WasteDepartment() {
        super();
    }

    public WasteDepartment(int departmentId, String departmentName, String description) {
        super(departmentId, departmentName, description);
    }

    @Override
    public void handleComplaint() {
        System.out.println("Waste Department is handling the waste management complaint.");
    }
}