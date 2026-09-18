package com.smartcity.service;

import com.smartcity.dao.ComplaintDAO;
import com.smartcity.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AIAnalyticsService {

    private ComplaintDAO complaintDAO;

    public AIAnalyticsService() {
        complaintDAO = new ComplaintDAO();
    }

    // ==========================================
    // AI PRIORITY ANALYTICS
    // ==========================================

    public void viewAIPriorityAnalytics() {

        String sql =
                "SELECT priority, COUNT(*) AS count " +
                        "FROM complaint " +
                        "GROUP BY priority " +
                        "ORDER BY FIELD(priority, 'HIGH', 'MEDIUM', 'LOW')";

        Map<String, Integer> priorityCounts =
                new HashMap<>();

        priorityCounts.put("HIGH", 0);
        priorityCounts.put("MEDIUM", 0);
        priorityCounts.put("LOW", 0);

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            while (resultSet.next()) {

                String priority =
                        resultSet.getString("priority");

                int count =
                        resultSet.getInt("count");

                priorityCounts.put(
                        priority.toUpperCase(),
                        count
                );
            }

            int high =
                    priorityCounts.get("HIGH");

            int medium =
                    priorityCounts.get("MEDIUM");

            int low =
                    priorityCounts.get("LOW");

            int total =
                    high + medium + low;

            System.out.println();
            System.out.println("==========================================");
            System.out.println("       AI PRIORITY ANALYTICS");
            System.out.println("==========================================");

            System.out.println(
                    "Total Complaints : " + total
            );

            System.out.println(
                    "HIGH Priority    : " + high
            );

            System.out.println(
                    "MEDIUM Priority  : " + medium
            );

            System.out.println(
                    "LOW Priority     : " + low
            );

            System.out.println("------------------------------------------");

            if (total > 0) {

                double highPercentage =
                        (high * 100.0) / total;

                double mediumPercentage =
                        (medium * 100.0) / total;

                double lowPercentage =
                        (low * 100.0) / total;

                System.out.printf(
                        "HIGH    : %.2f%%%n",
                        highPercentage
                );

                System.out.printf(
                        "MEDIUM  : %.2f%%%n",
                        mediumPercentage
                );

                System.out.printf(
                        "LOW     : %.2f%%%n",
                        lowPercentage
                );

            } else {

                System.out.println(
                        "No complaints available for analysis."
                );
            }

            System.out.println("==========================================");

        } catch (SQLException e) {

            System.out.println(
                    "Error while loading AI analytics: "
                            + e.getMessage()
            );
        }
    }
}