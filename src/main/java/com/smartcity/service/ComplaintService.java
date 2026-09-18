package com.smartcity.service;

import com.smartcity.dao.ComplaintDAO;
import com.smartcity.model.Complaint;
import com.smartcity.model.Department;
import com.smartcity.utils.InputUtil;

public class ComplaintService {

    private ComplaintDAO complaintDAO;
    private DepartmentService departmentService;
    private PriorityService priorityService;
    private SuggestionService suggestionService;
    private CitizenService citizenService;
    private AIService aiService;
    private AIRecommendationService aiRecommendationService;


    public ComplaintService() {

        complaintDAO = new ComplaintDAO();
        departmentService = new DepartmentService();
        priorityService = new PriorityService();
        suggestionService = new SuggestionService();
        citizenService = new CitizenService();
        aiService = new AIService();
        aiRecommendationService =
                new AIRecommendationService();
    }

    // =====================================================
// REGISTER COMPLAINT
// =====================================================

    public void registerComplaint(Complaint complaint) {

        System.out.println();
        System.out.println("========== REGISTER COMPLAINT ==========");

        // Check complaint object
        if (complaint == null) {

            System.out.println(
                    "Invalid complaint details."
            );

            return;
        }


        // =================================================
        // CHECK CITIZEN
        // =================================================

        int citizenId =
                complaint.getCitizenId();

        if (citizenId <= 0) {

            System.out.println(
                    "Invalid Citizen ID."
            );

            return;
        }


        if (!citizenService.citizenExists(citizenId)) {

            System.out.println(
                    "Citizen ID does not exist. Please register first."
            );

            return;
        }


        // =================================================
        // CATEGORY VALIDATION
        // =================================================

        String category =
                complaint.getCategory();

        if (category == null ||
                category.trim().isEmpty()) {

            System.out.println(
                    "Complaint category cannot be empty."
            );

            return;
        }

        category = category.trim();


        if (!category.equalsIgnoreCase("Traffic") &&
                !category.equalsIgnoreCase("Waste") &&
                !category.equalsIgnoreCase("Water") &&
                !category.equalsIgnoreCase("Electricity")) {

            System.out.println(
                    "Invalid complaint category."
            );

            return;
        }


        // Normalize category
        if (category.equalsIgnoreCase("Traffic")) {

            category = "Traffic";

        } else if (category.equalsIgnoreCase("Waste")) {

            category = "Waste";

        } else if (category.equalsIgnoreCase("Water")) {

            category = "Water";

        } else if (category.equalsIgnoreCase("Electricity")) {

            category = "Electricity";
        }


        complaint.setCategory(category);


        // =================================================
        // TITLE VALIDATION
        // =================================================

        String title =
                complaint.getTitle();

        if (title == null ||
                title.trim().isEmpty()) {

            System.out.println(
                    "Complaint title cannot be empty."
            );

            return;
        }

        title = title.trim();

        if (title.length() > 100) {

            System.out.println(
                    "Title cannot exceed 100 characters."
            );

            return;
        }

        complaint.setTitle(title);


        // =================================================
        // DESCRIPTION VALIDATION
        // =================================================

        String description =
                complaint.getDescription();

        if (description == null ||
                description.trim().isEmpty()) {

            System.out.println(
                    "Complaint description cannot be empty."
            );

            return;
        }

        description = description.trim();

        complaint.setDescription(description);


        // =================================================
        // LOCATION VALIDATION
        // =================================================

        String location =
                complaint.getLocation();

        if (location == null ||
                location.trim().isEmpty()) {

            System.out.println(
                    "Complaint location cannot be empty."
            );

            return;
        }

        location = location.trim();

        if (location.length() > 100) {

            System.out.println(
                    "Location cannot exceed 100 characters."
            );

            return;
        }

        complaint.setLocation(location);


        // =================================================
        // DATE VALIDATION
        // =================================================

        if (complaint.getComplaintDate() == null) {

            System.out.println(
                    "Complaint date cannot be empty."
            );

            return;
        }


        // =================================================
        // AUTOMATIC DEPARTMENT ASSIGNMENT
        // =================================================

        Department department =
                departmentService.getDepartmentForComplaint(complaint);
        if (department == null) {

            System.out.println(
                    "Unable to assign department."
            );

            return;
        }

        complaint.setDepartment(
                department.getDepartmentName()
        );


        // =================================================
        // DEFAULT STATUS
        // =================================================

        complaint.setStatus("Pending");


// =================================================
// AI-BASED PRIORITY PREDICTION
// =================================================

        String complaintText =
                complaint.getTitle() + " "
                        + complaint.getDescription();

        String aiPriority =
                aiService.predictPriority(complaintText);

        if (aiPriority != null &&
                !aiPriority.trim().isEmpty()) {

            complaint.setPriority(
                    aiPriority.trim().toUpperCase()
            );

            System.out.println(
                    "AI Priority Prediction : "
                            + complaint.getPriority()
            );

        } else {

            // Fallback to rule-based priority
            String fallbackPriority =
                    priorityService.calculatePriority(
                            complaint
                    );

            complaint.setPriority(
                    fallbackPriority
            );

            System.out.println(
                    "AI unavailable. Using rule-based priority."
            );
        }
        String aiRecommendation =
                aiRecommendationService.generateRecommendation(
                        complaintText,
                        complaint.getPriority()
                );

        complaint.setAiRecommendation(
                aiRecommendation
        );

        System.out.println(
                "AI Recommendation : "
                        + aiRecommendation
        );

        // =================================================
        // AUTOMATIC SUGGESTION
        // =================================================

        String suggestion =
                suggestionService.generateSuggestion(
                        complaint
                );

        complaint.setSuggestion(suggestion);


        // =================================================
        // SAVE COMPLAINT
        // =================================================

        complaintDAO.addComplaint(complaint);


        // =================================================
        // SUCCESS MESSAGE
        // =================================================

        System.out.println();

        System.out.println(
                "========== COMPLAINT REGISTERED SUCCESSFULLY =========="
        );

        System.out.println(
                "Category   : " +
                        complaint.getCategory()
        );

        System.out.println(
                "Department : " +
                        complaint.getDepartment()
        );

        System.out.println(
                "Priority   : " +
                        complaint.getPriority()
        );

        System.out.println(
                "Status     : " +
                        complaint.getStatus()
        );

        System.out.println(
                "Suggestion : " +
                        complaint.getSuggestion()
        );

        System.out.println(
                "========================================================"
        );


        // =================================================
        // DEPARTMENT-SPECIFIC HANDLING
        // =================================================

        department.handleComplaint();
    }



    // =====================================================
    // VIEW CITIZEN COMPLAINTS
    // =====================================================

    public void viewComplaintsByCitizen(int citizenId) {

        if (citizenId <= 0) {

            System.out.println(
                    "Invalid Citizen ID."
            );

            return;
        }


        if (!citizenService.citizenExists(citizenId)) {

            System.out.println(
                    "Citizen ID does not exist."
            );

            return;
        }


        complaintDAO.viewComplaintsByCitizen(
                citizenId
        );
    }


    // =====================================================
    // VIEW COMPLAINTS BY DEPARTMENT
    // =====================================================

    public void viewComplaintsByDepartment(
            String department) {

        if (department == null ||
                department.trim().isEmpty()) {

            System.out.println(
                    "Department cannot be empty."
            );

            return;
        }


        complaintDAO.viewComplaintsByDepartment(
                department.trim()
        );
    }


    // =====================================================
    // VIEW COMPLAINTS BY PRIORITY
    // =====================================================

    public void viewComplaintsByPriority(
            String priority) {

        if (priority == null ||
                priority.trim().isEmpty()) {

            System.out.println(
                    "Priority cannot be empty."
            );

            return;
        }


        String normalizedPriority =
                priority.trim().toUpperCase();


        if (!normalizedPriority.equals("HIGH")
                && !normalizedPriority.equals("MEDIUM")
                && !normalizedPriority.equals("LOW")) {

            System.out.println(
                    "Invalid priority."
            );

            return;
        }


        complaintDAO.viewComplaintsByPriority(
                normalizedPriority
        );
    }


    // =====================================================
    // VIEW HIGH PRIORITY PENDING COMPLAINTS
    // =====================================================

    public void viewHighPriorityPendingComplaints() {

        complaintDAO.viewHighPriorityPendingComplaints();
    }


    // =====================================================
    // VIEW COMPLAINTS BY
    // DEPARTMENT + PRIORITY + STATUS
    // =====================================================

    public void viewComplaintsByFilters(
            String department,
            String priority,
            String status) {


        // Validate department
        if (department == null ||
                department.trim().isEmpty()) {

            System.out.println(
                    "Department cannot be empty."
            );

            return;
        }


        // Validate priority
        if (priority == null ||
                priority.trim().isEmpty()) {

            System.out.println(
                    "Priority cannot be empty."
            );

            return;
        }


        // Validate status
        if (status == null ||
                status.trim().isEmpty()) {

            System.out.println(
                    "Status cannot be empty."
            );

            return;
        }


        String normalizedDepartment =
                department.trim();

        String normalizedPriority =
                priority.trim().toUpperCase();

        String normalizedStatus =
                status.trim();


        // Validate priority
        if (!normalizedPriority.equals("HIGH")
                && !normalizedPriority.equals("MEDIUM")
                && !normalizedPriority.equals("LOW")) {

            System.out.println(
                    "Invalid priority."
            );

            return;
        }


        // Validate status
        if (!normalizedStatus.equals("Pending")
                && !normalizedStatus.equals("In Progress")
                && !normalizedStatus.equals("Resolved")) {

            System.out.println(
                    "Invalid status."
            );

            return;
        }


        // Send validated data to DAO
        complaintDAO.viewComplaintsByFilters(
                normalizedDepartment,
                normalizedPriority,
                normalizedStatus
        );
    }


    // =====================================================
    // UPDATE COMPLAINT STATUS
    // =====================================================

    public void updateComplaintStatus(
            int complaintId,
            String newStatus) {


        if (complaintId <= 0) {

            System.out.println(
                    "Invalid Complaint ID."
            );

            return;
        }


        if (newStatus == null ||
                newStatus.trim().isEmpty()) {

            System.out.println(
                    "Status cannot be empty."
            );

            return;
        }


        String status =
                newStatus.trim();


        // Check whether the new status is valid
        if (!status.equals("Pending")
                && !status.equals("In Progress")
                && !status.equals("Resolved")) {

            System.out.println(
                    "Invalid status."
            );

            return;
        }


        // Get current status from database
        String currentStatus =
                complaintDAO.getComplaintStatus(
                        complaintId
                );


        if (currentStatus != null) {

            currentStatus =
                    currentStatus.trim();


            if (currentStatus.equalsIgnoreCase(
                    "pending")) {

                currentStatus = "Pending";

            } else if (currentStatus.equalsIgnoreCase(
                    "in progress")) {

                currentStatus = "In Progress";

            } else if (currentStatus.equalsIgnoreCase(
                    "resolved")) {

                currentStatus = "Resolved";
            }
        }


        // Complaint does not exist
        if (currentStatus == null) {

            System.out.println(
                    "Complaint not found."
            );

            return;
        }


        // Same status
        if (currentStatus.equals(status)) {

            System.out.println(
                    "Complaint is already in "
                            + status
                            + " status."
            );

            return;
        }


        // Pending → In Progress
        if (currentStatus.equals("Pending")
                && status.equals("In Progress")) {

            complaintDAO.updateComplaintStatus(
                    complaintId,
                    status
            );

            return;
        }


        // In Progress → Resolved
        if (currentStatus.equals("In Progress")
                && status.equals("Resolved")) {

            complaintDAO.updateComplaintStatus(
                    complaintId,
                    status
            );

            return;
        }


        // Any other transition is invalid
        System.out.println(
                "Invalid status transition: "
                        + currentStatus
                        + " → "
                        + status
        );
    }


    // =====================================================
    // DELETE COMPLAINT
    // =====================================================

    public void deleteComplaint(
            int complaintId) {

        if (complaintId <= 0) {

            System.out.println(
                    "Invalid Complaint ID."
            );

            return;
        }


        complaintDAO.deleteComplaint(
                complaintId
        );
    }


    // =====================================================
    // VIEW COMPLAINT SUMMARY
    // =====================================================

    public void viewComplaintSummary() {

        complaintDAO.viewComplaintSummary();
    }


    // =====================================================
    // CATEGORY-WISE COMPLAINT ANALYTICS
    // =====================================================

    public void viewComplaintsByCategory() {

        complaintDAO.viewComplaintsByCategory();
    }
    // =====================================================
// VIEW ALL COMPLAINTS
// =====================================================

    public void viewComplaints() {

        complaintDAO.viewComplaints();
    }
}