package com.smartcity.model;

public class TrafficDepartment extends Department implements ComplaintHandler {

    public TrafficDepartment() {
        super();
    }

    public TrafficDepartment(int departmentId, String departmentName, String description) {
        super(departmentId, departmentName, description);
    }

    @Override
    public void handleComplaint() {
        System.out.println("Traffic Department is handling the traffic complaint.");
    }
}