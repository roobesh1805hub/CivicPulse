package com.smartcity.web;

import com.smartcity.dao.ComplaintDAO;
import com.smartcity.model.Complaint;
import com.smartcity.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class CivicPulseController {

    private final ComplaintService complaintService;
    private final ComplaintDAO complaintDAO;

    public CivicPulseController() {

        complaintService = new ComplaintService();
        complaintDAO = new ComplaintDAO();
    }

    // =====================================================
    // HOME PAGE
    // =====================================================

    @GetMapping("/")
    public String home() {

        return """
            <!DOCTYPE html>
            <html lang="en">

            <head>

                <meta charset="UTF-8">
                <meta name="viewport"
                      content="width=device-width, initial-scale=1.0">

                <title>CivicPulse</title>

                <style>

                    * {
                        box-sizing: border-box;
                        margin: 0;
                        padding: 0;
                        font-family: Arial, sans-serif;
                    }

                    body {
                        background: #f4f7fb;
                        color: #1f2937;
                    }

                    .navbar {
                        background: #0f172a;
                        color: white;
                        padding: 20px 8%;
                        display: flex;
                        justify-content: space-between;
                        align-items: center;
                    }

                    .logo {
                        font-size: 25px;
                        font-weight: bold;
                    }

                    .nav-link {
                        color: white;
                        text-decoration: none;
                        background: #2563eb;
                        padding: 10px 18px;
                        border-radius: 8px;
                    }

                    .hero {
                        text-align: center;
                        padding: 70px 20px;
                        background: linear-gradient(
                            135deg,
                            #dbeafe,
                            #eff6ff
                        );
                    }

                    .hero h1 {
                        font-size: 45px;
                        color: #0f172a;
                        margin-bottom: 15px;
                    }

                    .hero p {
                        max-width: 700px;
                        margin: auto;
                        font-size: 18px;
                        line-height: 1.6;
                        color: #475569;
                    }

                    .button {
                        display: inline-block;
                        margin-top: 30px;
                        padding: 14px 25px;
                        background: #2563eb;
                        color: white;
                        text-decoration: none;
                        border-radius: 8px;
                        font-weight: bold;
                    }

                    .section {
                        padding: 50px 8%;
                        text-align: center;
                    }

                    .section h2 {
                        margin-bottom: 30px;
                        color: #0f172a;
                    }

                    .cards {
                        display: grid;
                        grid-template-columns:
                            repeat(auto-fit, minmax(220px, 1fr));
                        gap: 20px;
                    }

                    .card {
                        background: white;
                        padding: 25px;
                        border-radius: 12px;
                        box-shadow:
                            0 4px 12px rgba(0, 0, 0, 0.08);
                    }

                    .card h3 {
                        margin-bottom: 10px;
                        color: #2563eb;
                    }

                    .card p {
                        color: #64748b;
                        line-height: 1.5;
                    }

                    .footer {
                        text-align: center;
                        padding: 25px;
                        background: #0f172a;
                        color: #cbd5e1;
                        margin-top: 30px;
                    }

                </style>

            </head>

            <body>

                <div class="navbar">

                    <div class="logo">
                        🏙️ CivicPulse
                    </div>

                    <a class="nav-link"
                       href="/complaint.html">
                        Register Complaint
                    </a>

                </div>

                <section class="hero">

                    <h1>Smart Citizen Services</h1>

                    <p>
                        CivicPulse is a smart civic issue management
                        system for registering, tracking and analyzing
                        citizen complaints with intelligent
                        priority prediction and decision support.
                    </p>

                    <a class="button"
                       href="/complaint.html">
                        📝 Register a Complaint
                    </a>

                </section>

                <section class="section">

                    <h2>What CivicPulse Provides</h2>

                    <div class="cards">

                        <div class="card">

                            <h3>👤 Citizen Management</h3>

                            <p>
                                Manage citizen information and
                                complaint records.
                            </p>

                        </div>

                        <div class="card">

                            <h3>📝 Complaint Registration</h3>

                            <p>
                                Submit civic complaints with
                                category, description and location.
                            </p>

                        </div>

                        <div class="card">

                            <h3>🏢 Department Assignment</h3>

                            <p>
                                Complaints are automatically routed
                                to the relevant department.
                            </p>

                        </div>

                        <div class="card">

                            <h3>🤖 AI Priority Prediction</h3>

                            <p>
                                Complaint text can be analyzed to
                                determine priority.
                            </p>

                        </div>

                        <div class="card">

                            <h3>💡 AI Decision Support</h3>

                            <p>
                                Generate actionable recommendations
                                for important complaints.
                            </p>

                        </div>

                        <div class="card">

                            <h3>📊 Complaint Analytics</h3>

                            <p>
                                Analyze complaint priorities,
                                categories and statuses.
                            </p>

                        </div>

                    </div>

                </section>

                <div class="footer">

                    CivicPulse — Citizen Services with Intelligent
                    Issue Tracking and Community Insights

                </div>

            </body>

            </html>
            """;
    }


    // =====================================================
    // REGISTER COMPLAINT
    // =====================================================

    @PostMapping("/api/complaints")
    public String registerComplaint(

            @RequestParam("citizenId")
            int citizenId,

            @RequestParam("category")
            String category,

            @RequestParam("title")
            String title,

            @RequestParam("description")
            String description,

            @RequestParam("location")
            String location
    ) {

        // =================================================
        // CITIZEN ID VALIDATION
        // =================================================

        if (citizenId <= 0) {

            return """
                <!DOCTYPE html>
                <html>

                <head>
                    <title>
                        CivicPulse - Invalid Citizen ID
                    </title>
                </head>

                <body>

                    <h1>🏙️ CivicPulse</h1>

                    <h2>❌ Invalid Citizen ID</h2>

                    <p>
                        Please enter a valid Citizen ID.
                    </p>

                    <br>

                    <a href="/complaint.html">
                        📝 Try Again
                    </a>

                    <br><br>

                    <a href="/">
                        🏠 Back to Home
                    </a>

                </body>

                </html>
                """;
        }


        // =================================================
        // COMPLAINT FIELD VALIDATION
        // =================================================

        if (category == null || category.isBlank()
                || title == null || title.isBlank()
                || description == null || description.isBlank()
                || location == null || location.isBlank()) {

            return """
                <!DOCTYPE html>
                <html>

                <head>
                    <title>
                        CivicPulse - Invalid Complaint
                    </title>
                </head>

                <body>

                    <h1>🏙️ CivicPulse</h1>

                    <h2>❌ Invalid Complaint Details</h2>

                    <p>
                        Please fill in all complaint fields
                        before submitting.
                    </p>

                    <br>

                    <a href="/complaint.html">
                        📝 Try Again
                    </a>

                    <br><br>

                    <a href="/">
                        🏠 Back to Home
                    </a>

                </body>

                </html>
                """;
        }


        // =================================================
        // CREATE COMPLAINT OBJECT
        // =================================================

        Complaint complaint = new Complaint();

        complaint.setCitizenId(citizenId);
        complaint.setCategory(category);
        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setLocation(location);

        complaint.setComplaintDate(
                new Date(System.currentTimeMillis())
        );


        // =================================================
        // REGISTER USING EXISTING SERVICE
        // =================================================

        boolean registered =
                complaintService.registerComplaint(complaint);


        // =================================================
        // REGISTRATION FAILURE
        // =================================================

        if (!registered) {

            return """
                <!DOCTYPE html>
                <html>

                <head>
                    <title>
                        CivicPulse - Complaint Not Registered
                    </title>
                </head>

                <body>

                    <h1>🏙️ CivicPulse</h1>

                    <h2>❌ Complaint Not Registered</h2>

                    <p>
                        Your complaint could not be registered.
                        Please check your details and try again.
                    </p>

                    <br>

                    <a href="/complaint.html">
                        📝 Try Again
                    </a>

                    <br><br>

                    <a href="/">
                        🏠 Back to Home
                    </a>

                </body>

                </html>
                """;
        }


        // =================================================
// REGISTRATION SUCCESS
// =================================================

        return """
    <!DOCTYPE html>
    <html lang="en">

    <head>

        <meta charset="UTF-8">

        <title>
            CivicPulse - Complaint Registered
        </title>

        <style>

            body {
                font-family: Arial, sans-serif;
                background: #f4f7fb;
                padding: 40px;
                color: #1f2937;
            }

            .container {
                max-width: 600px;
                margin: auto;
                background: white;
                padding: 35px;
                border-radius: 12px;
                box-shadow:
                    0 4px 15px rgba(0, 0, 0, 0.08);
            }

            h1 {
                color: #0f172a;
            }

            h2 {
                color: #16a34a;
                margin-bottom: 20px;
            }

            .complaint-id {
                background: #eff6ff;
                border-left: 5px solid #2563eb;
                padding: 18px;
                margin: 20px 0;
                font-size: 20px;
                font-weight: bold;
                color: #1e40af;
            }

            .details {
                line-height: 1.8;
            }

            .button {
                display: inline-block;
                margin-top: 20px;
                padding: 12px 20px;
                background: #2563eb;
                color: white;
                text-decoration: none;
                border-radius: 8px;
                font-weight: bold;
            }

            .button.secondary {
                background: #475569;
            }

        </style>

    </head>

    <body>

        <div class="container">

            <h1>🏙️ CivicPulse</h1>

            <h2>✅ Complaint Submitted Successfully</h2>

            <p>
                Your complaint has been successfully
                registered in the CivicPulse system.
            </p>

            <div class="complaint-id">

                🆔 Complaint ID:
                %d

            </div>

            <div class="details">

                <p>
                    <b>Citizen ID:</b> %d
                </p>

                <p>
                    <b>Category:</b> %s
                </p>

                <p>
                    <b>Title:</b> %s
                </p>

                <p>
                    <b>Location:</b> %s
                </p>

                <p>
                    <b>Status:</b> Pending
                </p>

            </div>

            <p>
                Please save your Complaint ID.
                You can use it to track your complaint.
            </p>

            <a class="button"
               href="/track.html">

                🔎 Track Complaint

            </a>

            <a class="button secondary"
               href="/complaint.html">

                📝 Register Another

            </a>

            <br><br>

            <a href="/">
                🏠 Back to Home
            </a>

        </div>

    </body>

    </html>
    """.formatted(
                complaint.getComplaintId(),
                citizenId,
                category,
                title,
                location
        );
    }


    // =====================================================
    // TRACK COMPLAINT
    // =====================================================

    @GetMapping("/api/complaints/track")
    public String trackComplaint(

            @RequestParam("complaintId")
            int complaintId
    ) {

        if (complaintId <= 0) {

            return """
                <html>

                <body>

                    <h2>
                        ❌ Invalid Complaint ID
                    </h2>

                    <p>
                        Please enter a valid complaint ID.
                    </p>

                    <a href="/track.html">
                        🔎 Try Again
                    </a>

                </body>

                </html>
                """;
        }


        Complaint complaint =
                complaintService.getComplaintById(complaintId);


        if (complaint == null) {

            return """
                <html>

                <body>

                    <h2>
                        ❌ Complaint Not Found
                    </h2>

                    <p>
                        No complaint was found with
                        the given ID.
                    </p>

                    <a href="/track.html">
                        🔎 Try Again
                    </a>

                </body>

                </html>
                """;
        }


        return """
            <!DOCTYPE html>
            <html lang="en">

            <head>

                <meta charset="UTF-8">

                <title>
                    CivicPulse - Complaint Status
                </title>

            </head>

            <body>

                <h1>🏙️ CivicPulse</h1>

                <h2>🔎 Complaint Status</h2>

                <hr>

                <p>
                    <b>Complaint ID:</b> %d
                </p>

                <p>
                    <b>Citizen ID:</b> %d
                </p>

                <p>
                    <b>Category:</b> %s
                </p>

                <p>
                    <b>Title:</b> %s
                </p>

                <p>
                    <b>Location:</b> %s
                </p>

                <p>
                    <b>Department:</b> %s
                </p>

                <p>
                    <b>Priority:</b> %s
                </p>

                <p>
                    <b>Status:</b> %s
                </p>

                <p>
                    <b>Suggestion:</b> %s
                </p>

                <br>

                <a href="/track.html">
                    🔎 Track Another Complaint
                </a>

                <br><br>

                <a href="/">
                    🏠 Back to Home
                </a>

            </body>

            </html>
            """.formatted(
                complaintId,
                complaint.getCitizenId(),
                complaint.getCategory(),
                complaint.getTitle(),
                complaint.getLocation(),
                complaint.getDepartment(),
                complaint.getPriority(),
                complaint.getStatus(),
                complaint.getSuggestion()
        );
    }


    // =====================================================
    // ADMIN DASHBOARD SUMMARY API
    // =====================================================

    @GetMapping("/api/admin/summary")
    public String adminSummary() {

        int[] summary =
                complaintDAO.getDashboardSummary();

        return """
            {
                "total": %d,
                "pending": %d,
                "inProgress": %d,
                "resolved": %d,
                "high": %d,
                "medium": %d,
                "low": %d
            }
            """.formatted(
                summary[0],
                summary[1],
                summary[2],
                summary[3],
                summary[4],
                summary[5],
                summary[6]
        );
    }
    @GetMapping("/api/admin/departments")
    public String departmentSummary() {

        int[] departments =
                complaintDAO.getDepartmentCounts();

        return """
        {
            "traffic": %d,
            "waste": %d,
            "water": %d,
            "electricity": %d
        }
        """.formatted(
                departments[0],
                departments[1],
                departments[2],
                departments[3]
        );
    }
    @GetMapping("/api/admin/complaints")
    public String recentComplaints() {

        List<Complaint> complaints =
                complaintDAO.getRecentComplaints();

        StringBuilder json =
                new StringBuilder("[");

        for (int i = 0; i < complaints.size(); i++) {

            Complaint c = complaints.get(i);

            json.append("""
            {
                "id": %d,
                "citizenId": %d,
                "category": "%s",
                "title": "%s",
                "location": "%s",
                "department": "%s",
                "priority": "%s",
                "status": "%s"
            }
            """.formatted(
                    c.getComplaintId(),
                    c.getCitizenId(),
                    escapeJson(c.getCategory()),
                    escapeJson(c.getTitle()),
                    escapeJson(c.getLocation()),
                    escapeJson(c.getDepartment()),
                    escapeJson(c.getPriority()),
                    escapeJson(c.getStatus())
            ));

            if (i < complaints.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        return json.toString();
    }
    private String escapeJson(String value) {

        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
    @PostMapping("/api/admin/complaints/status")
    public String updateComplaintStatus(
            @RequestParam("complaintId") int complaintId,
            @RequestParam("status") String status) {

        if (complaintId <= 0) {
            return """
            {
                "success": false,
                "message": "Invalid complaint ID."
            }
            """;
        }

        if (status == null || status.isBlank()) {
            return """
            {
                "success": false,
                "message": "Status cannot be empty."
            }
            """;
        }

        String normalizedStatus =
                status.trim().toLowerCase();

        if (!normalizedStatus.equals("pending")
                && !normalizedStatus.equals("in progress")
                && !normalizedStatus.equals("resolved")) {

            return """
            {
                "success": false,
                "message": "Invalid status."
            }
            """;
        }

        complaintDAO.updateComplaintStatus(
                complaintId,
                status.trim()
        );

        return """
        {
            "success": true,
            "message": "Complaint status updated successfully."
        }
        """;
    }

}