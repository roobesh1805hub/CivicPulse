package com.smartcity.dao;

import com.smartcity.database.DBConnection;
import com.smartcity.model.Complaint;
import com.smartcity.utils.FileReportUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplaintDAO {

    // =====================================================
// ADD COMPLAINT
// =====================================================

    public boolean addComplaint(Complaint complaint) {

        String sql = "INSERT INTO complaint(" +
                "citizen_id, category, title, description, location, " +
                "status, priority, suggestion, complaint_date, department, " +
                "ai_recommendation) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, complaint.getCitizenId());
            ps.setString(2, complaint.getCategory());
            ps.setString(3, complaint.getTitle());
            ps.setString(4, complaint.getDescription());
            ps.setString(5, complaint.getLocation());
            ps.setString(6, complaint.getStatus());
            ps.setString(7, complaint.getPriority());
            ps.setString(8, complaint.getSuggestion());
            ps.setDate(9, complaint.getComplaintDate());
            ps.setString(10, complaint.getDepartment());
            ps.setString(11, complaint.getAiRecommendation());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Complaint Registered Successfully!"
                );

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to register complaint."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }

        return false;
    }



    // =====================================================
    // VIEW ALL COMPLAINTS
    // =====================================================

    public void viewComplaints() {

        String sql = "SELECT * FROM complaint";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            StringBuilder report =
                    new StringBuilder();

            report.append(
                    "\n========== COMPLAINT REPORT ==========\n"
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                report.append(
                        "\n-----------------------------------------\n"
                );

                report.append("Complaint ID : ")
                        .append(
                                rs.getInt("complaint_id")
                        )
                        .append("\n");

                report.append("Citizen ID   : ")
                        .append(
                                rs.getInt("citizen_id")
                        )
                        .append("\n");

                report.append("Category     : ")
                        .append(
                                rs.getString("category")
                        )
                        .append("\n");

                report.append("Title        : ")
                        .append(
                                rs.getString("title")
                        )
                        .append("\n");

                report.append("Description  : ")
                        .append(
                                rs.getString("description")
                        )
                        .append("\n");

                report.append("Location     : ")
                        .append(
                                rs.getString("location")
                        )
                        .append("\n");

                report.append("Status       : ")
                        .append(
                                rs.getString("status")
                        )
                        .append("\n");

                report.append("Priority     : ")
                        .append(
                                rs.getString("priority")
                        )
                        .append("\n");

                report.append("Department   : ")
                        .append(
                                rs.getString("department")
                        )
                        .append("\n");

                report.append("Suggestion   : ")
                        .append(
                                rs.getString("suggestion")
                        )
                        .append("\n");
                report.append("AI Recommendation : ")
                        .append(
                                rs.getString("ai_recommendation")
                        )
                        .append("\n");

                report.append("Date         : ")
                        .append(
                                rs.getDate("complaint_date")
                        )
                        .append("\n");
            }

            if (!found) {

                report.append(
                        "\nNo complaints found.\n"
                );

            } else {

                report.append(
                        "\n=======================================\n"
                );
            }

            System.out.println(report);

            FileReportUtil.saveReport(
                    report.toString()
            );

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve complaints."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }

    // =====================================================
// VIEW CITIZEN COMPLAINTS
// =====================================================

    public void viewComplaintsByCitizen(int citizenId) {

        String sql =
                "SELECT * FROM complaint " +
                        "WHERE citizen_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, citizenId);

            try (ResultSet rs = ps.executeQuery()) {

                StringBuilder report =
                        new StringBuilder();

                report.append(
                        "\n========== MY COMPLAINTS ==========\n"
                );

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    report.append(
                            "\n-----------------------------------------\n"
                    );

                    report.append("Complaint ID : ")
                            .append(rs.getInt("complaint_id"))
                            .append("\n");

                    report.append("Category     : ")
                            .append(rs.getString("category"))
                            .append("\n");

                    report.append("Title        : ")
                            .append(rs.getString("title"))
                            .append("\n");

                    report.append("Description  : ")
                            .append(rs.getString("description"))
                            .append("\n");

                    report.append("Location     : ")
                            .append(rs.getString("location"))
                            .append("\n");

                    report.append("Status       : ")
                            .append(rs.getString("status"))
                            .append("\n");

                    report.append("Priority     : ")
                            .append(rs.getString("priority"))
                            .append("\n");

                    report.append("Department   : ")
                            .append(rs.getString("department"))
                            .append("\n");

                    report.append("Suggestion   : ")
                            .append(rs.getString("suggestion"))
                            .append("\n");
                    report.append("AI Recommendation : ")
                            .append(rs.getString("ai_recommendation"))
                            .append("\n");

                    report.append("Date         : ")
                            .append(rs.getDate("complaint_date"))
                            .append("\n");
                }

                if (!found) {

                    report.append(
                            "\nNo complaints found for this citizen.\n"
                    );

                } else {

                    report.append(
                            "\n====================================\n"
                    );
                }

                System.out.println(report);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve citizen complaints."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }
    // =====================================================
// VIEW COMPLAINTS BY DEPARTMENT
// =====================================================

    public void viewComplaintsByDepartment(String department) {

        String sql =
                "SELECT * FROM complaint " +
                        "WHERE LOWER(department) = LOWER(?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, department);

            try (ResultSet rs = ps.executeQuery()) {

                StringBuilder report =
                        new StringBuilder();

                report.append(
                        "\n========== DEPARTMENT COMPLAINTS ==========\n"
                );

                report.append(
                        "Department : "
                ).append(department).append("\n");

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    report.append(
                            "\n-----------------------------------------\n"
                    );

                    report.append("Complaint ID : ")
                            .append(rs.getInt("complaint_id"))
                            .append("\n");

                    report.append("Citizen ID   : ")
                            .append(rs.getInt("citizen_id"))
                            .append("\n");

                    report.append("Category     : ")
                            .append(rs.getString("category"))
                            .append("\n");

                    report.append("Title        : ")
                            .append(rs.getString("title"))
                            .append("\n");

                    report.append("Location     : ")
                            .append(rs.getString("location"))
                            .append("\n");

                    report.append("Status       : ")
                            .append(rs.getString("status"))
                            .append("\n");

                    report.append("Priority     : ")
                            .append(rs.getString("priority"))
                            .append("\n");

                    report.append("Department   : ")
                            .append(rs.getString("department"))
                            .append("\n");
                    report.append("AI Recommendation : ")
                            .append(rs.getString("ai_recommendation"))
                            .append("\n");
                }

                if (!found) {

                    report.append(
                            "\nNo complaints found for this department.\n"
                    );

                } else {

                    report.append(
                            "\n============================================\n"
                    );
                }

                System.out.println(report);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve department complaints."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }
    // =====================================================
// VIEW COMPLAINTS BY PRIORITY
// =====================================================

    public void viewComplaintsByPriority(String priority) {

        String sql =
                "SELECT * FROM complaint " +
                        "WHERE LOWER(priority) = LOWER(?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, priority);

            try (ResultSet rs = ps.executeQuery()) {

                StringBuilder report =
                        new StringBuilder();

                report.append(
                        "\n========== PRIORITY COMPLAINTS ==========\n"
                );

                report.append(
                        "Priority : "
                ).append(priority.toUpperCase()).append("\n");

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    report.append(
                            "\n-----------------------------------------\n"
                    );

                    report.append("Complaint ID : ")
                            .append(rs.getInt("complaint_id"))
                            .append("\n");

                    report.append("Citizen ID   : ")
                            .append(rs.getInt("citizen_id"))
                            .append("\n");

                    report.append("Category     : ")
                            .append(rs.getString("category"))
                            .append("\n");

                    report.append("Title        : ")
                            .append(rs.getString("title"))
                            .append("\n");

                    report.append("Location     : ")
                            .append(rs.getString("location"))
                            .append("\n");

                    report.append("Status       : ")
                            .append(rs.getString("status"))
                            .append("\n");

                    report.append("Priority     : ")
                            .append(rs.getString("priority"))
                            .append("\n");

                    report.append("Department   : ")
                            .append(rs.getString("department"))
                            .append("\n");
                    report.append("AI Recommendation : ")
                            .append(rs.getString("ai_recommendation"))
                            .append("\n");
                }

                if (!found) {

                    report.append(
                            "\nNo complaints found with this priority.\n"
                    );

                } else {

                    report.append(
                            "\n==========================================\n"
                    );
                }

                System.out.println(report);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve priority complaints."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }
    // =====================================================
// VIEW HIGH PRIORITY PENDING COMPLAINTS
// =====================================================

    public void viewHighPriorityPendingComplaints() {

        String sql =
                "SELECT * FROM complaint " +
                        "WHERE LOWER(priority) = 'high' " +
                        "AND LOWER(status) = 'pending'";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            StringBuilder report =
                    new StringBuilder();

            report.append(
                    "\n========== HIGH PRIORITY PENDING COMPLAINTS ==========\n"
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                report.append(
                        "\n-----------------------------------------\n"
                );

                report.append("Complaint ID : ")
                        .append(rs.getInt("complaint_id"))
                        .append("\n");

                report.append("Citizen ID   : ")
                        .append(rs.getInt("citizen_id"))
                        .append("\n");

                report.append("Category     : ")
                        .append(rs.getString("category"))
                        .append("\n");

                report.append("Title        : ")
                        .append(rs.getString("title"))
                        .append("\n");

                report.append("Location     : ")
                        .append(rs.getString("location"))
                        .append("\n");

                report.append("Status       : ")
                        .append(rs.getString("status"))
                        .append("\n");

                report.append("Priority     : ")
                        .append(rs.getString("priority"))
                        .append("\n");

                report.append("Department   : ")
                        .append(rs.getString("department"))
                        .append("\n");
            }

            if (!found) {

                report.append(
                        "\nNo high-priority pending complaints found.\n"
                );

            } else {

                report.append(
                        "\n=======================================================\n"
                );
            }

            System.out.println(report);

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve high-priority complaints."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // UPDATE COMPLAINT STATUS
    // =====================================================

    public void updateComplaintStatus(
            int complaintId,
            String status
    ) {

        String sql =
                "UPDATE complaint SET status=? " +
                        "WHERE complaint_id=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, status);
            ps.setInt(2, complaintId);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Complaint Status Updated Successfully!"
                );

            } else {

                System.out.println(
                        "Complaint Not Found!"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to update complaint status."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // GET COMPLAINT STATUS
    // =====================================================

    public String getComplaintStatus(
            int complaintId
    ) {

        String sql =
                "SELECT status FROM complaint " +
                        "WHERE complaint_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, complaintId);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getString("status");
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to fetch complaint status."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }

        return null;
    }


    // =====================================================
    // DELETE COMPLAINT
    // =====================================================

    public void deleteComplaint(
            int complaintId
    ) {

        String sql =
                "DELETE FROM complaint " +
                        "WHERE complaint_id=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, complaintId);

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Complaint Deleted Successfully!"
                );

            } else {

                System.out.println(
                        "Complaint Not Found!"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to delete complaint."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // COMPLAINT SUMMARY
    // =====================================================

    // =====================================================
// COMPLAINT SUMMARY
// =====================================================

    // =====================================================
// COMPLAINT SUMMARY
// =====================================================

    public void viewComplaintSummary() {

        // ================= OVERALL SUMMARY =================

        String overallSql =
                "SELECT " +
                        "COUNT(*) AS total, " +
                        "SUM(CASE WHEN LOWER(status) = 'pending' " +
                        "THEN 1 ELSE 0 END) AS pending, " +
                        "SUM(CASE WHEN LOWER(status) = 'in progress' " +
                        "THEN 1 ELSE 0 END) AS in_progress, " +
                        "SUM(CASE WHEN LOWER(status) = 'resolved' " +
                        "THEN 1 ELSE 0 END) AS resolved, " +
                        "SUM(CASE WHEN LOWER(priority) = 'high' " +
                        "THEN 1 ELSE 0 END) AS high_count, " +
                        "SUM(CASE WHEN LOWER(priority) = 'medium' " +
                        "THEN 1 ELSE 0 END) AS medium_count, " +
                        "SUM(CASE WHEN LOWER(priority) = 'low' " +
                        "THEN 1 ELSE 0 END) AS low_count " +
                        "FROM complaint";


        // ================= DEPARTMENT SUMMARY =================

        String departmentSql =
                "SELECT department, COUNT(*) AS complaint_count " +
                        "FROM complaint " +
                        "WHERE department IS NOT NULL " +
                        "GROUP BY department";


        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement overallPs =
                        con.prepareStatement(overallSql);
                PreparedStatement departmentPs =
                        con.prepareStatement(departmentSql)
        ) {

            // ================= OVERALL SUMMARY =================

            try (ResultSet rs = overallPs.executeQuery()) {

                if (rs.next()) {

                    System.out.println();
                    System.out.println(
                            "========== COMPLAINT SUMMARY =========="
                    );

                    System.out.println(
                            "Total Complaints      : "
                                    + rs.getInt("total")
                    );

                    System.out.println(
                            "Pending               : "
                                    + rs.getInt("pending")
                    );

                    System.out.println(
                            "In Progress           : "
                                    + rs.getInt("in_progress")
                    );

                    System.out.println(
                            "Resolved              : "
                                    + rs.getInt("resolved")
                    );

                    System.out.println();
                    System.out.println(
                            "---------- Priority Summary ----------"
                    );

                    System.out.println(
                            "High Priority         : "
                                    + rs.getInt("high_count")
                    );

                    System.out.println(
                            "Medium Priority       : "
                                    + rs.getInt("medium_count")
                    );

                    System.out.println(
                            "Low Priority          : "
                                    + rs.getInt("low_count")
                    );
                }
            }


            // ================= DEPARTMENT SUMMARY =================

            System.out.println();
            System.out.println(
                    "---------- Department Summary ----------"
            );

            try (ResultSet rs = departmentPs.executeQuery()) {

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    System.out.println(
                            rs.getString("department")
                                    + " : "
                                    + rs.getInt("complaint_count")
                                    + " complaint(s)"
                    );
                }

                if (!found) {

                    System.out.println(
                            "No department-wise complaints found."
                    );
                }
            }

            System.out.println(
                    "========================================"
            );


        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve complaint summary."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }
    // =====================================================
// VIEW COMPLAINTS BY DEPARTMENT + PRIORITY + STATUS
// =====================================================

    public void viewComplaintsByFilters(
            String department,
            String priority,
            String status
    ) {

        String sql =
                "SELECT * FROM complaint " +
                        "WHERE LOWER(department) = LOWER(?) " +
                        "AND LOWER(priority) = LOWER(?) " +
                        "AND LOWER(status) = LOWER(?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, department);
            ps.setString(2, priority);
            ps.setString(3, status);

            try (ResultSet rs = ps.executeQuery()) {

                StringBuilder report =
                        new StringBuilder();

                report.append(
                        "\n========== FILTERED COMPLAINTS ==========\n"
                );

                report.append("Department : ")
                        .append(department)
                        .append("\n");

                report.append("Priority   : ")
                        .append(priority)
                        .append("\n");

                report.append("Status     : ")
                        .append(status)
                        .append("\n");

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    report.append(
                            "\n-----------------------------------------\n"
                    );

                    report.append("Complaint ID : ")
                            .append(
                                    rs.getInt("complaint_id")
                            )
                            .append("\n");

                    report.append("Citizen ID   : ")
                            .append(
                                    rs.getInt("citizen_id")
                            )
                            .append("\n");

                    report.append("Category     : ")
                            .append(
                                    rs.getString("category")
                            )
                            .append("\n");

                    report.append("Title        : ")
                            .append(
                                    rs.getString("title")
                            )
                            .append("\n");

                    report.append("Description  : ")
                            .append(
                                    rs.getString("description")
                            )
                            .append("\n");

                    report.append("Location     : ")
                            .append(
                                    rs.getString("location")
                            )
                            .append("\n");

                    report.append("Status       : ")
                            .append(
                                    rs.getString("status")
                            )
                            .append("\n");

                    report.append("Priority     : ")
                            .append(
                                    rs.getString("priority")
                            )
                            .append("\n");

                    report.append("Department   : ")
                            .append(
                                    rs.getString("department")
                            )
                            .append("\n");

                    report.append("Suggestion   : ")
                            .append(
                                    rs.getString("suggestion")
                            )
                            .append("\n");
                    report.append("AI Recommendation : ")
                            .append(
                                    rs.getString("ai_recommendation")
                            )
                            .append("\n");

                    report.append("Date         : ")
                            .append(
                                    rs.getDate("complaint_date")
                            )
                            .append("\n");
                }

                if (!found) {

                    report.append(
                            "\nNo complaints found with these filters.\n"
                    );

                } else {

                    report.append(
                            "\n==========================================\n"
                    );
                }

                System.out.println(report);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve filtered complaints."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }
    // =====================================================
    // CATEGORY-WISE COMPLAINT ANALYTICS
    // =====================================================

    public void viewComplaintsByCategory() {

        String sql =
                "SELECT category, COUNT(*) AS complaint_count " +
                        "FROM complaint " +
                        "GROUP BY category " +
                        "ORDER BY complaint_count DESC";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(sql);
                ResultSet rs =
                        ps.executeQuery()
        ) {

            System.out.println();
            System.out.println(
                    "========== CATEGORY-WISE COMPLAINT ANALYTICS =========="
            );

            boolean found = false;

            int totalComplaints = 0;

            while (rs.next()) {

                found = true;

                String category =
                        rs.getString("category");

                int count =
                        rs.getInt("complaint_count");

                totalComplaints += count;

                System.out.println(
                        String.format(
                                "%-25s : %d complaint(s)",
                                category,
                                count
                        )
                );
            }

            if (!found) {

                System.out.println(
                        "No complaints available for analysis."
                );

            } else {

                System.out.println(
                        "-------------------------------------------------------"
                );

                System.out.println(
                        String.format(
                                "%-25s : %d",
                                "Total Complaints",
                                totalComplaints
                        )
                );
            }

            System.out.println(
                    "======================================================="
            );

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve category-wise analytics."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }
}