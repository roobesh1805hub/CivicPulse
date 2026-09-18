package com.smartcity.dao;

import com.smartcity.database.DBConnection;
import com.smartcity.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    public Admin login(String username, String password) {

        String sql =
                "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Admin admin = new Admin();

                    admin.setAdminId(
                            rs.getInt("admin_id")
                    );

                    admin.setUsername(
                            rs.getString("username")
                    );

                    admin.setPassword(
                            rs.getString("password")
                    );

                    return admin;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to perform admin login."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }

        return null;
    }
}