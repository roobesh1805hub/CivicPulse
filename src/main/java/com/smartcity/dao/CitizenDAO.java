package com.smartcity.dao;

import com.smartcity.database.DBConnection;
import com.smartcity.model.Citizen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CitizenDAO {

    // =====================================================
    // ADD CITIZEN
    // =====================================================

    public void addCitizen(Citizen citizen) {

        String sql = "INSERT INTO citizen(name, email, phone, password, address) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, citizen.getName());
            ps.setString(2, citizen.getEmail());
            ps.setString(3, citizen.getPhone());
            ps.setString(4, citizen.getPassword());
            ps.setString(5, citizen.getAddress());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Citizen Added Successfully!");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to add citizen. Please check the entered details."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // VIEW CITIZENS
    // =====================================================

    public void viewCitizens() {

        String sql = "SELECT * FROM citizen";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("----------------------------");
                System.out.println(
                        "Citizen ID : " +
                                rs.getInt("citizen_id")
                );

                System.out.println(
                        "Name       : " +
                                rs.getString("name")
                );

                System.out.println(
                        "Email      : " +
                                rs.getString("email")
                );

                System.out.println(
                        "Phone      : " +
                                rs.getString("phone")
                );

                System.out.println(
                        "Address    : " +
                                rs.getString("address")
                );
            }

            if (!found) {
                System.out.println("No citizens found.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to retrieve citizens."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // CHECK WHETHER CITIZEN EXISTS
    // =====================================================

    public boolean citizenExists(int citizenId) {

        String sql =
                "SELECT citizen_id FROM citizen WHERE citizen_id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, citizenId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return true;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to verify citizen."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }

        return false;
    }


    // =====================================================
    // DELETE CITIZEN
    // =====================================================

    public void deleteCitizen(int id) {

        String sql =
                "DELETE FROM citizen WHERE citizen_id=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Citizen Deleted Successfully!"
                );

            } else {

                System.out.println(
                        "Citizen Not Found!"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to delete citizen."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }


    // =====================================================
    // UPDATE ADDRESS
    // =====================================================

    public void updateAddress(int id, String address) {

        String sql =
                "UPDATE citizen SET address=? WHERE citizen_id=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, address);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println(
                        "Address Updated Successfully!"
                );

            } else {

                System.out.println(
                        "Citizen Not Found!"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Unable to update citizen address."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );
        }
    }
}