package com.smartcity;

import com.smartcity.model.Complaint;
import com.smartcity.model.Department;
import com.smartcity.service.DepartmentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {

    @Test
    void testElectricityDepartmentAssignment() {

        DepartmentService departmentService = new DepartmentService();

        Complaint complaint = new Complaint();
        complaint.setCategory("Electricity");

        Department department =
                departmentService.getDepartmentForComplaint(complaint);

        assertNotNull(department);

    }
}