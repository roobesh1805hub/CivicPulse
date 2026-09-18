package com.smartcity.service;

import com.smartcity.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AIRecommendationMigrationService {

    private AIRecommendationService recommendationService;

    public AIRecommendationMigrationService() {
        recommendationService =
                new AIRecommendationService();
    }

    public void updateMissingRecommendations() {

        String selectSql =
                "SELECT complaint_id, title, description, priority " +
                        "FROM complaint " +
                        "WHERE ai_recommendation IS NULL " +
                        "OR TRIM(ai_recommendation) = ''";

        String updateSql =
                "UPDATE complaint " +
                        "SET ai_recommendation = ? " +
                        "WHERE complaint_id = ?";

        int updatedCount = 0;

        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement selectStatement =
                        connection.prepareStatement(selectSql);

                PreparedStatement updateStatement =
                        connection.prepareStatement(updateSql);

                ResultSet resultSet =
                        selectStatement.executeQuery()
        ) {

            while (resultSet.next()) {

                int complaintId =
                        resultSet.getInt("complaint_id");

                String title =
                        resultSet.getString("title");

                String description =
                        resultSet.getString("description");

                String priority =
                        resultSet.getString("priority");

                String complaintText =
                        title + " " + description;

                String recommendation =
                        recommendationService
                                .generateRecommendation(
                                        complaintText,
                                        priority
                                );

                updateStatement.setString(
                        1,
                        recommendation
                );

                updateStatement.setInt(
                        2,
                        complaintId
                );

                int rows =
                        updateStatement.executeUpdate();

                if (rows > 0) {

                    updatedCount++;

                    System.out.println(
                            "Updated Complaint ID: "
                                    + complaintId
                    );

                    System.out.println(
                            "AI Recommendation: "
                                    + recommendation
                    );

                    System.out.println(
                            "------------------------------------------"
                    );
                }
            }

            System.out.println();
            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "AI RECOMMENDATION MIGRATION COMPLETED"
            );

            System.out.println(
                    "Complaints Updated : "
                            + updatedCount
            );

            System.out.println(
                    "=========================================="
            );

        } catch (SQLException e) {

            System.out.println(
                    "Unable to update missing AI recommendations."
            );

            System.out.println(
                    "Database Error: "
                            + e.getMessage()
            );
        }
    }
}