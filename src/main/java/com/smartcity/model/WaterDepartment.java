package com.smartcity.model;

public class WaterDepartment extends Department implements ComplaintHandler {

    public WaterDepartment() {
        super();
    }

    public WaterDepartment(int departmentId, String departmentName, String description) {
        super(departmentId, departmentName, description);
    }

    @Override
    public void handleComplaint() {
        System.out.println("Water Department is handling the water-related complaint.");
    }
}