package com.smartcity.service;

import com.smartcity.model.Complaint;

public class PriorityService {

    public String calculatePriority(Complaint complaint) {

        if (complaint == null) {
            return "LOW";
        }

        String text =
                (complaint.getCategory() + " "
                        + complaint.getTitle() + " "
                        + complaint.getDescription())
                        .toLowerCase();

        // HIGH PRIORITY
        if (text.contains("ambulance")
                || text.contains("accident")
                || text.contains("fire")
                || text.contains("emergency")
                || text.contains("danger")) {

            return "HIGH";
        }

        // MEDIUM PRIORITY
        if (text.contains("traffic")
                || text.contains("leakage")
                || text.contains("water")
                || text.contains("electricity")) {

            return "MEDIUM";
        }

        // DEFAULT
        return "LOW";
    }
}