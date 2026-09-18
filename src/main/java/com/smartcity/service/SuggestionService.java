package com.smartcity.service;

import com.smartcity.model.Complaint;

public class SuggestionService {

    public String generateSuggestion(Complaint complaint) {

        if (complaint == null) {
            return "No suggestion available.";
        }

        String category = complaint.getCategory();

        if (category == null || category.trim().isEmpty()) {
            return "Please provide a valid complaint category.";
        }

        switch (category.trim().toLowerCase()) {

            case "traffic":
                return "Traffic control team should inspect the reported location.";

            case "waste":
                return "Waste collection team should inspect the reported location.";

            case "water":
                return "Water department should inspect the reported leakage.";

            case "electricity":
                return "Electricity department should inspect the reported electrical issue.";

            default:
                return "The concerned department should inspect the reported issue.";
        }
    }
}