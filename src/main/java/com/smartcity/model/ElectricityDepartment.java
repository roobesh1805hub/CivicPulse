package com.smartcity.model;

public class ElectricityDepartment extends Department implements ComplaintHandler {

    public ElectricityDepartment() {
        super();
    }

    public ElectricityDepartment(int departmentId, String departmentName, String description) {
        super(departmentId, departmentName, description);
    }

    @Override
    public void handleComplaint() {
        System.out.println("Electricity Department is handling the electricity-related complaint.");
    }
}