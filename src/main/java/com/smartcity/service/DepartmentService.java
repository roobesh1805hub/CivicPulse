package com.smartcity.service;

import com.smartcity.model.Complaint;
import com.smartcity.model.Department;
import com.smartcity.model.TrafficDepartment;
import com.smartcity.model.WasteDepartment;
import com.smartcity.model.WaterDepartment;
import com.smartcity.model.ElectricityDepartment;

import java.util.HashMap;

public class DepartmentService {
    private HashMap<String, Department> departmentMap;
    public DepartmentService() {

        departmentMap = new HashMap<>();

        departmentMap.put(
                "traffic",
                new TrafficDepartment(
                        101,
                        "Traffic Department",
                        "Handles traffic-related complaints"
                )
        );

        departmentMap.put(
                "waste",
                new WasteDepartment(
                        102,
                        "Waste Management Department",
                        "Handles waste-related complaints"
                )
        );

        departmentMap.put(
                "water",
                new WaterDepartment(
                        103,
                        "Water Department",
                        "Handles water-related complaints"
                )
        );

        departmentMap.put(
                "electricity",
                new ElectricityDepartment(
                        104,
                        "Electricity Department",
                        "Handles electricity-related complaints"
                )
        );
    }

    public Department getDepartmentForComplaint(Complaint complaint) {

        if (complaint == null) {
            System.out.println("Complaint cannot be empty.");
            return null;
        }

        String category = complaint.getCategory();

        if (category == null || category.trim().isEmpty()) {
            System.out.println("Complaint category cannot be empty.");
            return null;
        }

        String normalizedCategory = category.trim().toLowerCase();

        Department department = departmentMap.get(normalizedCategory);

        if (department == null) {

            System.out.println(
                    "No department found for category: " + category
            );

            return null;
        }

        return department;
    }

    public void viewDepartments() {

        System.out.println("\n========== Departments ==========");

        for (Department department : departmentMap.values()) {

            System.out.println(
                    "Department ID : "
                            + department.getDepartmentId()
            );

            System.out.println(
                    "Department Name : "
                            + department.getDepartmentName()
            );

            System.out.println(
                    "Description : "
                            + department.getDescription()
            );

            System.out.println("----------------------------------");
        }
    }
}