package com.smartcity;

import com.smartcity.service.AdminService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    @Test
    void testInvalidAdminLogin() {

        AdminService adminService = new AdminService();

        boolean result = adminService.login(
                "admin@smartcity.com",
                "wrong123"
        );

        assertFalse(result);
    }
}