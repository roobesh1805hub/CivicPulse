package com.smartcity.service;

import com.smartcity.dao.CitizenDAO;
import com.smartcity.model.Citizen;

public class CitizenService {

    private CitizenDAO citizenDAO;

    // Constructor
    public CitizenService() {
        citizenDAO = new CitizenDAO();
    }

    // Add Citizen
    public void addCitizen(Citizen citizen) {

        if (citizen == null) {
            System.out.println("Citizen data cannot be empty.");
            return;
        }

        if (citizen.getName() == null ||
                citizen.getName().trim().isEmpty()) {

            System.out.println("Name cannot be empty.");
            return;
        }

        if (citizen.getEmail() == null ||
                citizen.getEmail().trim().isEmpty()) {

            System.out.println("Email cannot be empty.");
            return;
        }

        if (citizen.getPhone() == null ||
                citizen.getPhone().trim().isEmpty()) {

            System.out.println("Phone cannot be empty.");
            return;
        }

        citizenDAO.addCitizen(citizen);
    }

    // View Citizens
    public void viewCitizens() {
        citizenDAO.viewCitizens();
    }
    // =====================================================
// CHECK WHETHER CITIZEN EXISTS
// =====================================================

    public boolean citizenExists(int citizenId) {

        if (citizenId <= 0) {
            return false;
        }

        return citizenDAO.citizenExists(citizenId);
    }

    public void updateAddress(int citizenId, String address) {

        if (citizenId <= 0) {
            System.out.println("Invalid Citizen ID.");
            return;
        }

        if (address == null || address.trim().isEmpty()) {
            System.out.println("Address cannot be empty.");
            return;
        }

        citizenDAO.updateAddress(citizenId, address);
    }

    // Delete Citizen
    public void deleteCitizen(int citizenId) {

        if (citizenId <= 0) {
            System.out.println("Invalid Citizen ID.");
            return;
        }

        citizenDAO.deleteCitizen(citizenId);
    }
}