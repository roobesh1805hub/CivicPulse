package com.smartcity.service;

import com.smartcity.dao.AdminDAO;
import com.smartcity.model.Admin;

public class AdminService {

    private AdminDAO adminDAO;

    public AdminService() {
        adminDAO = new AdminDAO();
    }

    // =====================================================
    // VALIDATE ADMIN
    // =====================================================

    public boolean validateAdmin(Admin admin) {

        if (admin == null) {
            System.out.println("Admin data cannot be empty.");
            return false;
        }

        if (admin.getUsername() == null ||
                admin.getUsername().trim().isEmpty()) {

            System.out.println("Admin username cannot be empty.");
            return false;
        }

        if (admin.getPassword() == null ||
                admin.getPassword().trim().isEmpty()) {

            System.out.println("Admin password cannot be empty.");
            return false;
        }

        return true;
    }


    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    public boolean login(String username, String password) {

        // Validate username
        if (username == null ||
                username.trim().isEmpty()) {

            System.out.println("Username cannot be empty.");
            return false;
        }

        // Validate password
        if (password == null ||
                password.trim().isEmpty()) {

            System.out.println("Password cannot be empty.");
            return false;
        }

        Admin admin =
                adminDAO.login(username, password);

        if (admin != null) {

            System.out.println(
                    "Admin Login Successful!"
            );

            return true;
        }

        System.out.println(
                "Invalid Admin Username or Password."
        );

        return false;
    }
}