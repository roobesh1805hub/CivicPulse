package com.smartcity.service;

public class AIRecommendationService {

    public String generateRecommendation(
            String complaintText,
            String priority) {

        if (complaintText == null ||
                complaintText.trim().isEmpty()) {

            return "No complaint information available.";
        }

        if (priority == null ||
                priority.trim().isEmpty()) {

            return "Review the complaint manually.";
        }

        String text =
                complaintText.toLowerCase();

        String recommendation;

        // Traffic-related complaints
        if (text.contains("traffic") ||
                text.contains("ambulance") ||
                text.contains("accident") ||
                text.contains("signal")) {

            if (priority.equalsIgnoreCase("HIGH")) {

                recommendation =
                        "Immediately alert traffic control "
                                + "and clear the affected route.";

            } else {

                recommendation =
                        "Traffic control team should inspect "
                                + "the reported location.";
            }

            // Water-related complaints
        } else if (text.contains("water") ||
                text.contains("pipe") ||
                text.contains("leak") ||
                text.contains("pipeline") ||
                text.contains("flood")) {

            if (priority.equalsIgnoreCase("HIGH")) {

                recommendation =
                        "Immediately dispatch the water department "
                                + "and control the affected water flow.";

            } else {

                recommendation =
                        "Water department should inspect "
                                + "the reported leakage or pipeline.";
            }

            // Waste-related complaints
        } else if (text.contains("garbage") ||
                text.contains("waste") ||
                text.contains("trash") ||
                text.contains("overflow")) {

            if (priority.equalsIgnoreCase("HIGH")) {

                recommendation =
                        "Immediately arrange waste collection "
                                + "and inspect the affected area.";

            } else {

                recommendation =
                        "Schedule waste collection and inspect "
                                + "the affected area.";
            }

            // Electricity-related complaints
        } else if (text.contains("electricity") ||
                text.contains("power") ||
                text.contains("street light") ||
                text.contains("light") ||
                text.contains("transformer")) {

            if (priority.equalsIgnoreCase("HIGH")) {

                recommendation =
                        "Immediately dispatch the electricity "
                                + "department and secure the affected area.";

            } else {

                recommendation =
                        "Electricity department should inspect "
                                + "the reported issue.";
            }

            // General complaint
        } else {

            recommendation =
                    "Assign the complaint to the appropriate "
                            + "department for inspection.";
        }

        return recommendation;
    }
}