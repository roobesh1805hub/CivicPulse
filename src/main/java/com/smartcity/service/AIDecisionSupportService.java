package com.smartcity.service;

import com.smartcity.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AIDecisionSupportService {

    public void viewHighPriorityPendingComplaints() {

        String sql =
                "SELECT complaint_id, category, title, description, " +
                        "location, priority, status, ai_recommendation " +
                        "FROM complaint " +
                        "WHERE LOWER(priority) = 'high' " +
                        "AND LOWER(status) = 'pending' " +
                        "ORDER BY complaint_id";
        try (
                Connection connection =
                        DBConnection.getConnection();

                PreparedStatement statement =
                        connection.prepareStatement(sql);

                ResultSet resultSet =
                        statement.executeQuery()
        ) {

            System.out.println();
            System.out.println("==========================================");
            System.out.println("       AI DECISION SUPPORT");
            System.out.println("==========================================");

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                int complaintId =
                        resultSet.getInt("complaint_id");

                String category =
                        resultSet.getString("category");

                String title =
                        resultSet.getString("title");

                String description =
                        resultSet.getString("description");

                String location =
                        resultSet.getString("location");

                String priority =
                        resultSet.getString("priority");

                String status =
                        resultSet.getString("status");

                String recommendation =
                        resultSet.getString("ai_recommendation");
                System.out.println();
                System.out.println("------------------------------------------");

                System.out.println(
                        "Complaint ID : " + complaintId
                );

                System.out.println(
                        "Category     : " + category
                );

                System.out.println(
                        "Title        : " + title
                );

                System.out.println(
                        "Description  : " + description
                );

                System.out.println(
                        "Location     : " + location
                );

                System.out.println(
                        "Priority     : " + priority
                );

                System.out.println(
                        "Status       : " + status
                );

                System.out.println();

                System.out.println(
                        "AI Recommendation:"
                );

                System.out.println(
                        recommendation
                );
            }

            if (!found) {

                System.out.println();
                System.out.println(
                        "No HIGH priority pending complaints found."
                );
            }

            System.out.println();
            System.out.println("==========================================");

        } catch (SQLException e) {

            System.out.println(
                    "Error loading AI decision support: "
                            + e.getMessage()
            );
        }
    }
}