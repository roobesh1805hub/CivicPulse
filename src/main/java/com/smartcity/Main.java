package com.smartcity;

import com.smartcity.model.Citizen;
import com.smartcity.model.Complaint;
import com.smartcity.service.AIAnalyticsService;
import com.smartcity.service.AdminService;
import com.smartcity.service.CitizenService;
import com.smartcity.service.ComplaintService;
import com.smartcity.service.DepartmentService;
import com.smartcity.utils.InputUtil;
import com.smartcity.service.AIDecisionSupportService;

import java.sql.Date;

public class Main {

    static CitizenService citizenService =
            new CitizenService();

    static ComplaintService complaintService =
            new ComplaintService();

    static AdminService adminService =
            new AdminService();

    static DepartmentService departmentService =
            new DepartmentService();

    static AIAnalyticsService aiAnalyticsService =
            new AIAnalyticsService();
    static AIDecisionSupportService aiDecisionSupportService =
            new AIDecisionSupportService();


    // =====================================================
    // MAIN PROGRAM
    // =====================================================

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n======================================");
            System.out.println("     SMART CITY INNOVATION SYSTEM");
            System.out.println("======================================");

            System.out.println("1. Citizen Management");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");

            int choice =
                    InputUtil.readInt("Enter your choice : ");


            switch (choice) {

                case 1:

                    citizenMenu();

                    break;


                case 2:

                    adminLogin();

                    break;


                case 3:

                    System.out.println(
                            "Thank you for using Smart City System."
                    );

                    return;


                default:

                    System.out.println(
                            "Invalid Choice!"
                    );
            }
        }
    }


    // =====================================================
    // CITIZEN MENU
    // =====================================================

    public static void citizenMenu() {

        while (true) {

            System.out.println(
                    "\n========== Citizen Menu =========="
            );

            System.out.println("1. Add Citizen");
            System.out.println("2. View Citizens");
            System.out.println("3. Update Address");
            System.out.println("4. Delete Citizen");
            System.out.println("5. Complaint Management");
            System.out.println("6. Back");

            int choice =
                    InputUtil.readInt(
                            "Enter your choice : "
                    );


            switch (choice) {

                // -----------------------------------------
                // ADD CITIZEN
                // -----------------------------------------

                case 1:

                    Citizen citizen =
                            new Citizen();


                    citizen.setName(
                            InputUtil.readString(
                                    "Enter Name : "
                            )
                    );


                    citizen.setEmail(
                            InputUtil.readEmail(
                                    "Enter Email : "
                            )
                    );


                    citizen.setPhone(
                            InputUtil.readPhone(
                                    "Enter Phone : "
                            )
                    );


                    citizen.setPassword(
                            InputUtil.readString(
                                    "Enter Password : "
                            )
                    );


                    citizen.setAddress(
                            InputUtil.readString(
                                    "Enter Address : "
                            )
                    );


                    citizenService.addCitizen(
                            citizen
                    );

                    break;


                // -----------------------------------------
                // VIEW CITIZENS
                // -----------------------------------------

                case 2:

                    citizenService.viewCitizens();

                    break;


                // -----------------------------------------
                // UPDATE ADDRESS
                // -----------------------------------------

                case 3:

                    int id =
                            InputUtil.readInt(
                                    "Enter Citizen ID : "
                            );


                    String address =
                            InputUtil.readString(
                                    "Enter New Address : "
                            );


                    citizenService.updateAddress(
                            id,
                            address
                    );

                    break;


                // -----------------------------------------
                // DELETE CITIZEN
                // -----------------------------------------

                case 4:

                    int deleteId =
                            InputUtil.readInt(
                                    "Enter Citizen ID : "
                            );


                    citizenService.deleteCitizen(
                            deleteId
                    );

                    break;


                // -----------------------------------------
                // COMPLAINT MANAGEMENT
                // -----------------------------------------

                case 5:

                    complaintMenu();

                    break;


                // -----------------------------------------
                // BACK
                // -----------------------------------------

                case 6:

                    return;


                default:

                    System.out.println(
                            "Invalid Choice!"
                    );
            }
        }
    }


    // =====================================================
    // COMPLAINT MENU
    // =====================================================

    public static void complaintMenu() {

        while (true) {

            System.out.println(
                    "\n========== Complaint Menu =========="
            );

            System.out.println("1. Register Complaint");
            System.out.println("2. View My Complaints");
            System.out.println("3. Back");

            int choice =
                    InputUtil.readInt(
                            "Enter your choice : "
                    );


            switch (choice) {

                // -----------------------------------------
                // REGISTER COMPLAINT
                // -----------------------------------------

                case 1:

                    Complaint complaint =
                            new Complaint();


                    int citizenId =
                            InputUtil.readInt(
                                    "Enter Citizen ID : "
                            );


                    complaint.setCitizenId(
                            citizenId
                    );


                    System.out.println(
                            "\n========== Select Complaint Category =========="
                    );

                    System.out.println("1. Traffic");
                    System.out.println("2. Waste");
                    System.out.println("3. Water");
                    System.out.println("4. Electricity");

                    int categoryChoice =
                            InputUtil.readInt(
                                    "Enter Category Choice : "
                            );


                    String selectedCategory;


                    switch (categoryChoice) {

                        case 1:

                            selectedCategory =
                                    "Traffic";

                            break;


                        case 2:

                            selectedCategory =
                                    "Waste";

                            break;


                        case 3:

                            selectedCategory =
                                    "Water";

                            break;


                        case 4:

                            selectedCategory =
                                    "Electricity";

                            break;


                        default:

                            System.out.println(
                                    "Invalid Category Choice!"
                            );

                            continue;
                    }


                    complaint.setCategory(
                            selectedCategory
                    );


                    complaint.setTitle(
                            InputUtil.readString(
                                    "Enter Title : "
                            )
                    );


                    complaint.setDescription(
                            InputUtil.readString(
                                    "Enter Description : "
                            )
                    );


                    complaint.setLocation(
                            InputUtil.readString(
                                    "Enter Location : "
                            )
                    );


                    complaint.setComplaintDate(
                            new Date(
                                    System.currentTimeMillis()
                            )
                    );


                    complaintService.registerComplaint(
                            complaint
                    );

                    break;


                // -----------------------------------------
                // VIEW COMPLAINTS
                // -----------------------------------------

                case 2:

                    int viewCitizenId =
                            InputUtil.readInt(
                                    "Enter Citizen ID : "
                            );


                    complaintService.viewComplaintsByCitizen(
                            viewCitizenId
                    );

                    break;


                // -----------------------------------------
                // BACK
                // -----------------------------------------

                case 3:

                    return;


                default:

                    System.out.println(
                            "Invalid Choice!"
                    );
            }
        }
    }


    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    public static void adminLogin() {

        System.out.println(
                "\n========== Admin Login =========="
        );


        String username =
                InputUtil.readString(
                        "Enter Admin Username : "
                );


        String password =
                InputUtil.readString(
                        "Enter Admin Password : "
                );


        boolean loginSuccess =
                adminService.login(
                        username,
                        password
                );


        if (loginSuccess) {

            adminMenu();
        }
    }


    // =====================================================
    // ADMIN DASHBOARD
    // =====================================================

    public static void adminMenu() {

        while (true) {

            System.out.println(
                    "\n========== Admin Dashboard =========="
            );

            System.out.println(
                    "Welcome, Admin!"
            );


            System.out.println(
                    "1. View All Complaints"
            );


            System.out.println(
                    "2. View Complaint Summary"
            );


            System.out.println(
                    "3. Update Complaint Status"
            );


            System.out.println(
                    "4. View Departments"
            );


            System.out.println(
                    "5. View Complaints by Department"
            );


            System.out.println(
                    "6. View Complaints by Priority"
            );


            System.out.println(
                    "7. View High Priority Pending Complaints"
            );


            System.out.println(
                    "8. View All Citizens"
            );


            System.out.println(
                    "9. View Complaints by Department + Priority + Status"
            );


            System.out.println(
                    "10. View Category-wise Complaint Analytics"
            );


            // -----------------------------------------
            // NEW AI FEATURE
            // -----------------------------------------

            System.out.println(
                    "11. AI Priority Analytics"
            );


            System.out.println("12. AI Decision Support");
            System.out.println("13. Back / Logout");


            int choice =
                    InputUtil.readInt(
                            "Enter your choice : "
                    );


            switch (choice) {

                // -----------------------------------------
                // VIEW ALL COMPLAINTS
                // -----------------------------------------

                case 1:

                    complaintService.viewComplaints();

                    break;


                // -----------------------------------------
                // VIEW COMPLAINT SUMMARY
                // -----------------------------------------

                case 2:

                    complaintService.viewComplaintSummary();

                    break;


                // -----------------------------------------
                // UPDATE COMPLAINT STATUS
                // -----------------------------------------

                case 3:

                    int complaintId =
                            InputUtil.readInt(
                                    "Enter Complaint ID : "
                            );


                    System.out.println(
                            "\n========== Update Complaint Status =========="
                    );


                    System.out.println(
                            "1. Pending"
                    );


                    System.out.println(
                            "2. In Progress"
                    );


                    System.out.println(
                            "3. Resolved"
                    );


                    int statusChoice =
                            InputUtil.readInt(
                                    "Enter Status Choice : "
                            );


                    String status;


                    switch (statusChoice) {

                        case 1:

                            status =
                                    "Pending";

                            break;


                        case 2:

                            status =
                                    "In Progress";

                            break;


                        case 3:

                            status =
                                    "Resolved";

                            break;


                        default:

                            System.out.println(
                                    "Invalid Status Choice!"
                            );

                            continue;
                    }


                    complaintService.updateComplaintStatus(
                            complaintId,
                            status
                    );

                    break;


                // -----------------------------------------
                // VIEW DEPARTMENTS
                // -----------------------------------------

                case 4:

                    departmentService.viewDepartments();

                    break;


                // -----------------------------------------
                // VIEW COMPLAINTS BY DEPARTMENT
                // -----------------------------------------

                case 5:

                    System.out.println(
                            "\n========== Select Department =========="
                    );


                    System.out.println(
                            "1. Traffic Department"
                    );


                    System.out.println(
                            "2. Waste Management Department"
                    );


                    System.out.println(
                            "3. Water Department"
                    );


                    System.out.println(
                            "4. Electricity Department"
                    );


                    int departmentChoice =
                            InputUtil.readInt(
                                    "Enter Department Choice : "
                            );


                    String selectedDepartment;


                    switch (departmentChoice) {

                        case 1:

                            selectedDepartment =
                                    "Traffic Department";

                            break;


                        case 2:

                            selectedDepartment =
                                    "Waste Management Department";

                            break;


                        case 3:

                            selectedDepartment =
                                    "Water Department";

                            break;


                        case 4:

                            selectedDepartment =
                                    "Electricity Department";

                            break;


                        default:

                            System.out.println(
                                    "Invalid Department Choice!"
                            );

                            continue;
                    }


                    complaintService.viewComplaintsByDepartment(
                            selectedDepartment
                    );

                    break;


                // -----------------------------------------
                // VIEW COMPLAINTS BY PRIORITY
                // -----------------------------------------

                case 6:

                    System.out.println(
                            "\n========== Select Priority =========="
                    );


                    System.out.println(
                            "1. HIGH"
                    );


                    System.out.println(
                            "2. MEDIUM"
                    );


                    System.out.println(
                            "3. LOW"
                    );


                    int priorityChoice =
                            InputUtil.readInt(
                                    "Enter Priority Choice : "
                            );


                    String selectedPriority;


                    switch (priorityChoice) {

                        case 1:

                            selectedPriority =
                                    "HIGH";

                            break;


                        case 2:

                            selectedPriority =
                                    "MEDIUM";

                            break;


                        case 3:

                            selectedPriority =
                                    "LOW";

                            break;


                        default:

                            System.out.println(
                                    "Invalid Priority Choice!"
                            );

                            continue;
                    }


                    complaintService.viewComplaintsByPriority(
                            selectedPriority
                    );

                    break;


                // -----------------------------------------
                // HIGH PRIORITY + PENDING
                // -----------------------------------------

                case 7:

                    complaintService
                            .viewHighPriorityPendingComplaints();

                    break;


                // -----------------------------------------
                // VIEW ALL CITIZENS
                // -----------------------------------------

                case 8:

                    citizenService.viewCitizens();

                    break;


                // -----------------------------------------
                // COMBINED FILTER
                // DEPARTMENT + PRIORITY + STATUS
                // -----------------------------------------

                case 9:

                    System.out.println(
                            "\n========== Select Department =========="
                    );


                    System.out.println(
                            "1. Traffic Department"
                    );


                    System.out.println(
                            "2. Waste Management Department"
                    );


                    System.out.println(
                            "3. Water Department"
                    );


                    System.out.println(
                            "4. Electricity Department"
                    );


                    int filterDepartmentChoice =
                            InputUtil.readInt(
                                    "Enter Department Choice : "
                            );


                    String filterDepartment;


                    switch (filterDepartmentChoice) {

                        case 1:

                            filterDepartment =
                                    "Traffic Department";

                            break;


                        case 2:

                            filterDepartment =
                                    "Waste Management Department";

                            break;


                        case 3:

                            filterDepartment =
                                    "Water Department";

                            break;


                        case 4:

                            filterDepartment =
                                    "Electricity Department";

                            break;


                        default:

                            System.out.println(
                                    "Invalid Department Choice!"
                            );

                            continue;
                    }


                    // -------------------------------------
                    // SELECT PRIORITY
                    // -------------------------------------

                    System.out.println(
                            "\n========== Select Priority =========="
                    );


                    System.out.println(
                            "1. HIGH"
                    );


                    System.out.println(
                            "2. MEDIUM"
                    );


                    System.out.println(
                            "3. LOW"
                    );


                    int filterPriorityChoice =
                            InputUtil.readInt(
                                    "Enter Priority Choice : "
                            );


                    String filterPriority;


                    switch (filterPriorityChoice) {

                        case 1:

                            filterPriority =
                                    "HIGH";

                            break;


                        case 2:

                            filterPriority =
                                    "MEDIUM";

                            break;


                        case 3:

                            filterPriority =
                                    "LOW";

                            break;


                        default:

                            System.out.println(
                                    "Invalid Priority Choice!"
                            );

                            continue;
                    }


                    // -------------------------------------
                    // SELECT STATUS
                    // -------------------------------------

                    System.out.println(
                            "\n========== Select Status =========="
                    );


                    System.out.println(
                            "1. Pending"
                    );


                    System.out.println(
                            "2. In Progress"
                    );


                    System.out.println(
                            "3. Resolved"
                    );


                    int filterStatusChoice =
                            InputUtil.readInt(
                                    "Enter Status Choice : "
                            );


                    String filterStatus;


                    switch (filterStatusChoice) {

                        case 1:

                            filterStatus =
                                    "Pending";

                            break;


                        case 2:

                            filterStatus =
                                    "In Progress";

                            break;


                        case 3:

                            filterStatus =
                                    "Resolved";

                            break;


                        default:

                            System.out.println(
                                    "Invalid Status Choice!"
                            );

                            continue;
                    }


                    // -------------------------------------
                    // CALL SERVICE
                    // -------------------------------------

                    complaintService.viewComplaintsByFilters(
                            filterDepartment,
                            filterPriority,
                            filterStatus
                    );

                    break;


                // -----------------------------------------
                // CATEGORY-WISE COMPLAINT ANALYTICS
                // -----------------------------------------

                case 10:

                    complaintService
                            .viewComplaintsByCategory();

                    break;


                // -----------------------------------------
                // AI PRIORITY ANALYTICS
                // -----------------------------------------

                case 11:

                    aiAnalyticsService
                            .viewAIPriorityAnalytics();

                    break;


                // -----------------------------------------
                // AI DECISION SUPPORT
                // -----------------------------------------

                case 12:
                    aiDecisionSupportService
                            .viewHighPriorityPendingComplaints();
                    break;

                case 13:
                    System.out.println(
                            "Admin logged out successfully."
                    );
                    return;


                // -----------------------------------------
                // INVALID CHOICE
                // -----------------------------------------

                default:

                    System.out.println(
                            "Invalid Choice!"
                    );
            }
        }
    }
}